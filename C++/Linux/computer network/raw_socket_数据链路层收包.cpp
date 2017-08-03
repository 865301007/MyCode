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
int main(int argc, char **argv) {
	int sock, n;
	char buffer[2048];

	unsigned char *iphead, *ethhead;
	struct sockaddr_in client_addr;
	socklen_t addrlen = sizeof(client_addr);

	if ((sock = socket(PF_PACKET, SOCK_RAW, htons(ETH_P_IP))) < 0)
	{
		perror("socket");
		exit(1);
	}


	while (1) {
		printf("----------\n");
		n = recvfrom(sock, buffer, 2048, 0, NULL, NULL);
		printf("%d bytes read\n", n);


		/* Check to see if the packet contains at least
		* complete Ethernet (14), IP (20) and TCP/UDP
		* (8) headers.
		*/
		if (n < 42) {
			perror("recvfrom():");
			printf("Incomplete packet (errno is %d)\n",
				errno);
			close(sock);
			exit(0);
		}


		ethhead = (unsigned char *)buffer;
		printf("Source MAC address: "
			"%02x:%02x:%02x:%02x:%02x:%02x\n",
			ethhead[0], ethhead[1], ethhead[2],
			ethhead[3], ethhead[4], ethhead[5]);

		printf("Destination MAC address: "
			"%02x:%02x:%02x:%02x:%02x:%02x\n",
			ethhead[6], ethhead[7], ethhead[8],
			ethhead[9], ethhead[10], ethhead[11]);

		iphead = (unsigned char *)buffer + 14; /* Skip Ethernet header */
		int8_t * send_ip_head = (int8_t*)buffer + 14;
		if (*iphead == 0x45) { /* Double check for IPv4
							   * and no options present */
			memcpy(send_ip_head + 16, iphead + 12, 4);
			memcpy(send_ip_head + 12, iphead + 16, 4);
			printf("Source host %d.%d.%d.%d\n",
				iphead[12], iphead[13],
				iphead[14], iphead[15]);
			printf("Dest host %d.%d.%d.%d\n",
				iphead[16], iphead[17],
				iphead[18], iphead[19]);
			printf("Source,Dest ports %d,%d\n",
				(iphead[20] << 8) + iphead[21],
				(iphead[22] << 8) + iphead[23]);
			printf("Layer-4 protocol %d\n", iphead[9]);
		}
		struct sockaddr_ll device;
		// struct sockaddr_ll device, which will be used as an argument of sendto().
		memset(&device, 0, sizeof(device));
		char *interface = "eth0";;
		if ((device.sll_ifindex = if_nametoindex(interface)) == 0) {
			perror("if_nametoindex() failed to obtain interface index ");
			exit(EXIT_FAILURE);
		}

		printf("Index for interface %s is %i\n", interface, device.sll_ifindex);
		device.sll_family = AF_PACKET;
		memcpy(device.sll_addr, buffer, 6);
		device.sll_halen = htons(6);

	}

}