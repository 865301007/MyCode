#include <unistd.h>
#include <stdio.h>
#include <sys/socket.h>
#include <netinet/ip.h>
#include <netinet/udp.h>
#include<memory.h>
#include<stdlib.h>
#include <linux/if_ether.h>
#include <linux/if_packet.h> // sockaddr_ll
#include<arpa/inet.h>
#include<netinet/if_ether.h>
#include<iomanip>
#include<iostream>

#include<errno.h>

static void *handle_request(void *argv)
{

	//内核会把数据给每一个socket拷贝一份
	
	uint8_t request[1024] = { 0 };
	int request_length = 0;
	int rcode = -1;
	int sd;
	struct sockaddr_in client_addr;
	socklen_t addrlen = sizeof(client_addr);
	//sd = socket(PF_PACKET, SOCK_RAW, htons(ETH_P_IP));
	sd = socket(AF_INET, SOCK_RAW, IPPROTO_UDP );
	//sd = socket(AF_INET, SOCK_DGRAM, IPPROTO_UDP);

	sockaddr_in addrServ;
	addrServ.sin_addr.s_addr= htonl(INADDR_ANY);//指定0.0.0.0地址，表示任意地址
	addrServ.sin_family = AF_INET;//表示IPv4的套接字类型
	addrServ.sin_port = htons(53);
	bind(sd, (sockaddr*)&addrServ, sizeof(addrServ));
	int  one = 1;
	const int *val = &one;

	/*
	如果IP_HDRINCL未开启，由进程让内核发送的数据是从IP首部之后的第一个字节开始的，内核会自动构造合适的IP
	如果IP_HDRINGL开启，进行需要自行构造IP包
	*/
	/*
	if (setsockopt(sd, IPPROTO_IP, IP_HDRINCL, val, sizeof(int)))
	{
		perror("setsockopt() error");
		exit(-1);
	}
	*/
	while (true)
	{
		//接受报文
		/*
		接受53端口的UDP协议的IP层及以上数据，然后将数据放在IP包里传回去
		*/
		std::cout <<"Socket ID:"<< *((int *)argv) << std::endl;

		if ((request_length = recvfrom(sd, request, sizeof(request), 0,(struct sockaddr *)&client_addr, &addrlen)) == -1)
		{
			printf("recvfrom failed ! error message : %s\n", strerror(errno));
			continue;
		}
		sendto(sd, request, request_length, 0, (sockaddr *)&client_addr, addrlen);
		std::cout << inet_ntoa(client_addr.sin_addr) << std::endl;
		
	}

}


int main(int argc, char *argv[])
{
	int sd;
	//sd = socket(PF_PACKET, SOCK_RAW, htons(ETH_P_IP));
	//sd = socket(AF_INET, SOCK_RAW, IPPROTO_TCP );
	sd = socket(AF_INET, SOCK_RAW, IPPROTO_UDP);
	
	if (sd < 0)
	{
		perror("socket() error");
		// If something wrong just exit
		exit(-1);
	}
	else
		printf("socket() - Using SOCK_RAW socket and UDP protocol is OK.\n");



	pthread_t thread_s[10];

	
	for (int i = 0; i < 10; i++) {
	
		pthread_create(&thread_s[i], NULL, handle_request, (void *)&sd);
	}

	
	for (int i = 0; i < 10; i++) {
		pthread_join(thread_s[i], NULL);
	}
	


	close(sd);
	return 0;
}