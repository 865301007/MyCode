/*
二层包
*/

#include<inttypes.h>
#include<sys/socket.h>
#include <stdio.h>//for perror
#include<errno.h>
#include<stdlib.h>
#include <unistd.h>//for close
#include <sys/syscall.h>
#include<netinet/ip.h>
#include<linux/if_ether.h> //ETH_P_IP
#include<linux/if_packet.h>//sockaddr_ll

#include <iostream>
#include <string>


#include"responser.h"
#include"checker.h"


int  PTHREAD_NUM = 1;//线程数




static void *handle_request(void *argv)
{
	/*Socket初始化部分*/
	int sd = socket(PF_PACKET, SOCK_RAW, htons(ETH_P_ALL));
	if (sd < 0)
	{
		perror("socket() error");
		// If something wrong just exit
		exit(-1);
	}


	//以下是在循环中要用到的变量，定义到循环外从而提升效率
	uint8_t send_buffer[2048] = { 0 };
	uint8_t receive_buffer[2048] = { 0 };
	int send_buffer_length = 0;
	int receive_buffer_length = 0;


	uint8_t * dns_request = NULL;
	uint8_t * dns_response = NULL;
	int dns_request_length = 0;
	int dns_response_length = 0;


	Checker checker;
	Responser responser;
	int rcode = 0;

	struct sockaddr_ll client_addr;
	socklen_t addrlen = sizeof(sockaddr_ll);
	int thread_id = *((int *)argv);

	iphdr * receive_ip_head = (iphdr *)(receive_buffer + 14);
	udphdr * receive_udp_head = (udphdr *)(receive_buffer + 34);
	iphdr * send_ip_head = (iphdr *)(send_buffer + 14);
	udphdr *send_udp_head = (udphdr *)(send_buffer + 34);
	ethhdr *receive_ethhdr_head = (ethhdr *)receive_buffer;
	int select_code = 0;//来决定该线程是否处理数据报



	while (true)
	{
	
	
		if ((receive_buffer_length = recvfrom(sd, receive_buffer, sizeof(receive_buffer), 0, (struct sockaddr *)&client_addr, &addrlen)) == -1)
		{
			printf("recvfrom failed ! error message : %s\n", strerror(errno));
			continue;
		}
		if (receive_ethhdr_head->h_proto!=htons(0x0800))
		{
			continue;//不是IP报文
		}

		if (receive_ip_head->protocol != IPPROTO_UDP)
		{
			continue;//不是UDP报文
		}
	
		if (receive_udp_head->dest != htons(53))
		{
			continue;//不是53端口的
		}


		select_code = uint32_t(receive_ip_head->id + receive_ip_head->check + receive_udp_head->source) % PTHREAD_NUM;
		if (select_code != thread_id)
		{
			continue;
		}

		//检查查询报文
		dns_request = receive_buffer + 42;
		dns_request_length = receive_buffer_length - 42;
		if ((rcode = checker.check_request(dns_request, dns_request_length)) == -1)
		{
			continue;//不是合法DNS报文
		}

		//构造响应报文
		dns_response = send_buffer + 42;
		responser.construct_response(dns_request, dns_request_length, rcode, receive_ip_head->saddr, dns_response, dns_response_length);

		//构造udp头部
		responser.construct_udp_header(receive_ip_head->daddr, receive_ip_head->saddr, receive_udp_head->dest, receive_udp_head->source, dns_response_length, send_buffer + 34);


		memcpy(send_buffer, receive_buffer, 34);//拷贝帧+ip头
		memcpy(send_buffer, receive_buffer + 6, 6);
		memcpy(send_buffer + 6, receive_buffer, 6);//交换帧的目的地址和源地址
		memcpy(send_buffer + 26, receive_buffer + 30, 4);
		memcpy(send_buffer + 30, receive_buffer + 26, 4);//交换ip的目的地址和源地址

	
		send_ip_head->check = 0;
		send_ip_head->tot_len = htons(20 + 8 + dns_response_length);
		send_ip_head->check = responser.csum(((uint16_t *)send_ip_head), 10);
	

		send_buffer_length = 42 + dns_response_length;
		if (sendto(sd, send_buffer, send_buffer_length, 0, (struct sockaddr *) &client_addr, sizeof(client_addr)) == -1)
		{
			printf("sendto failed ! error message :%s\n", strerror(errno));
			break;
		}
		//std::cout << "send" << std::endl;
		memset(receive_buffer, 0, sizeof(receive_buffer));
		memset(send_buffer, 0, sizeof(send_buffer));


	}
	close(sd);
}

int main(int argc, char *argv[])
{
	
	pid_t pid = fork();
	if (pid < 0)
	{
	std::cout << "fork() error" << std::endl;
	exit(-1);
	}
	else if (pid > 0)
	{
	std::cout << "server has been started!" << std::endl;
	exit(0);
	}
	



	if (argc > 1)
	{
		PTHREAD_NUM = atoi(argv[1]);
		if (PTHREAD_NUM <= 0)
		{
			std::cout << "Parameter error" << std::endl;
			exit(-1);
		}
	}

	//此处是为了占领端口，避免系统给出ICMP错误
	int  place_hold = socket(AF_INET, SOCK_DGRAM, IPPROTO_UDP);
	sockaddr_in addrServ;
	addrServ.sin_addr.s_addr = htonl(INADDR_ANY);
	addrServ.sin_family = AF_INET;
	addrServ.sin_port = htons(53);
	bind(place_hold, (sockaddr*)&addrServ, sizeof(addrServ));



	//创建线程来处理
	pthread_t thread_s[PTHREAD_NUM];
	int pthread_id[PTHREAD_NUM];
	for (int i = 0; i < PTHREAD_NUM; i++) {
		pthread_id[i] = i;
		pthread_create(&thread_s[i], NULL, handle_request, (void *)&pthread_id[i]);
	}

	//等待线程结束
	for (int i = 0; i < PTHREAD_NUM; i++) {
		pthread_join(thread_s[i], NULL);
	}

	return 0;
}
