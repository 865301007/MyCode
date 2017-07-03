#pragma once
#include<netinet/ip.h>
#include<arpa/inet.h>
#include<memory.h>
#include<iostream>
#include<netinet/udp.h>
#include"UDPBuilder.h"
#include"IPInfo.h"
struct IPPocket
{
	__uint8_t * IPdata;
	unsigned int length;
};
class IPBuilder
{
public:
	IPBuilder();
	~IPBuilder();
	IPBuilder(IPInfo &IPinfoArg,UDPPocket &udpPocketArg);
	IPPocket getIPPocket();

private:
	UDPPocket udpPocket;
	IPInfo ipInfo;
	void setUDPCheckSum();
	//����У���
	unsigned short csum(unsigned short *buf, int nwords);

};

