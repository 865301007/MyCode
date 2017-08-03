//捕获二层包，并把数据原路返回
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
#include<netinet/ether.h>
#include<net/if.h>
#include<iostream>
using namespace std;
int main(int argc, char **argv) {
	int sock, n;
	char buffer[2048];


	if ((sock = socket(PF_PACKET, SOCK_RAW, htons(ETH_P_IP))) < 0)
	{
		perror("socket");
		exit(1);
	}

	struct sockaddr_ll client;
	socklen_t addr_length = sizeof(sockaddr_ll);
	int8_t sendbuffer[2048];

	while (1) {
		n = recvfrom(sock, buffer, 2048, 0, (sockaddr *)&client, &addr_length);
		if (n < 42) {
			continue;
		}
		cout << AF_PACKET << "   " << client.sll_family << endl;
		cout << htons(6) << "    " << client.sll_halen << endl;


		memcpy(sendbuffer, buffer, n);
		memcpy(sendbuffer, buffer + 6, 6);
		memcpy(sendbuffer + 6, buffer, 6);

		memcpy(sendbuffer + 14 + 12, buffer + 14 + 16, 4);
		memcpy(sendbuffer + 14 + 16, buffer + 14 + 12, 4);

		//memset(sendbuffer + 14 + 20, 0, n - 14 - 20);

		n = sendto(sock, sendbuffer, n, 0, (struct sockaddr *) &client, sizeof(client));
		cout << n << endl;

	}

}