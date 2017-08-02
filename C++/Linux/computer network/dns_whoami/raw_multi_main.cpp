
//多线程原始套接字，从三层包开始
#include<inttypes.h>
#include<sys/socket.h>
#include <stdio.h>//for perror
#include<errno.h>
#include<stdlib.h>
#include <unistd.h>//for close
#include <sys/syscall.h>
#include<netinet/ip.h>

#include <iostream>
#include <string>


#include"responser.h"
#include"checker.h"


int  PTHREAD_NUM = 10;




static void *handle_request(void *argv)
{
	/*Socket初始化部分*/
	int sd = socket(AF_INET, SOCK_RAW, IPPROTO_UDP);
	if (sd < 0)
	{
		perror("socket() error");
		// If something wrong just exit
		exit(-1);
	}


	
	sockaddr_in addrServ;
	addrServ.sin_addr.s_addr = htonl(INADDR_ANY);//指定0.0.0.0地址，表示任意地址
	addrServ.sin_family = AF_INET;//表示IPv4的套接字类型
	addrServ.sin_port = htons(53);
	bind(sd, (sockaddr*)&addrServ, sizeof(addrServ));
	//Socket初始化结束
	

	int receive_length=0,request_length = 0;
	int send_length=0,response_length = 0;
	uint8_t receive_buffer[1024] = { 0 };
	uint8_t _send_buffer[1024] = { 0 };
	uint8_t *send_buffer = _send_buffer + 20;//缓冲区前边留下空间便于udp校验和的计算
	Checker checker;
	Responser responser;
	int rcode = 0;


	struct sockaddr_in client_addr;
	socklen_t addrlen = sizeof(client_addr);

	int thread_id = *((int *)argv);

	//std::cout << thread_id << std::endl;
	while (true)
	{
		//接受报文,此处接受的报文是IP+UDP+DNS
		//std::cout << thread_id<< std::endl;
		if ((receive_length = recvfrom(sd, receive_buffer, sizeof(receive_buffer), 0, (struct sockaddr *)&client_addr, &addrlen)) == -1)
		{
			printf("recvfrom failed ! error message : %s\n", strerror(errno));
			continue;
		}

		
		int select_code = uint32_t((((iphdr*)receive_buffer)->check - ((udphdr *)(receive_buffer + 20))->check + ((iphdr*)receive_buffer)->id)) % PTHREAD_NUM;
	//	std::cout << select_code << std::endl;
	
		
		if (select_code!=thread_id)
		{
			continue;
		}
		
		//检查报文
		uint8_t *request = receive_buffer + 28;
		request_length = receive_length - 28;
		if ((rcode = checker.check_request(request, request_length)) == -1)
		{
			continue;
		}

		//构造响应报文
		uint8_t *response = send_buffer  + 8;//前边留出一些字节便于计算udp校验和
		                                         //response指响应的DNS报文
		responser.construct_response(request, request_length, rcode, client_addr.sin_addr.s_addr, response, response_length);
		responser.construct_udp_header(((iphdr*)receive_buffer)->daddr, ((iphdr*)receive_buffer)->saddr, //构造udp头部
			((udphdr *)(receive_buffer+20))->dest, ((udphdr *)(receive_buffer+20))->source, response_length, send_buffer);

		
		//发送响应
		send_length = response_length + 8;

		//std::cout << thread_id << std::endl;
		if (sendto(sd, send_buffer, send_length, 0, (struct sockaddr *) &client_addr, sizeof(client_addr)) == -1)
		{
			printf("sendto failed ! error message :%s\n", strerror(errno));
			break;
		}
		memset(receive_buffer, 0, sizeof(receive_buffer));
		memset(_send_buffer, 0, sizeof(_send_buffer));
		
	}
	close(sd);
}

int main(int argc,char *argv[])
{
	/*
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
	*/

	//此处是为了占领端口，避免系统给出ICMP错误

	if (argc>1)
	{
		PTHREAD_NUM = atoi(argv[1]);
	}
	int  place_hold = socket(AF_INET, SOCK_DGRAM, IPPROTO_UDP);
	sockaddr_in addrServ;
	addrServ.sin_addr.s_addr = htonl(INADDR_ANY);
	addrServ.sin_family = AF_INET;
	addrServ.sin_port = htons(53);
	bind(place_hold, (sockaddr*)&addrServ, sizeof(addrServ));
	/*
	uint8_t buffer[1024] = { 0 };
	while (true)
	{
		recvfrom(place_hold, buffer, sizeof(buffer), 0, NULL, NULL);
	}
	*/
	

	
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
