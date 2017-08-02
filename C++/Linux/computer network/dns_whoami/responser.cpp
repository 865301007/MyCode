#include "responser.h"
/*
@summary 构造响应包
@param request 指向请求包的指针
@param request_length 请求包的长度
@param rcode 响应包的rcode
@param ip ip地址

*/

void Responser::construct_response(const uint8_t * request, int request_length, int rcode, const uint32_t ip, uint8_t * response, int & response_length)
{
	response_length = 0;
	memcpy(response, request, 12);
	uint16_t flag = ntohs(((DNS_Head *)response)->FLAGS);
	flag &= 0xF910;//请求报文中的AA，TC，RA，Z，AD，Rcode等标记位一般要求写0，但如果没有写0，可以容忍，
				   //响应报文中按照要求填入正确的值
	flag |= 0x8000;//QR填1
	((DNS_Head *)response)->FLAGS = htons(flag);


	switch (rcode)
	{
	case 0:this->construct_response_for_rcode0(request, request_length, ip, response, response_length); break;
	case 1:this->construct_response_for_rcode1(request, request_length, response, response_length); break;
	case 2:this->construct_response_for_rcode2(request, request_length, response, response_length); break;
	default:
		break;
	}
}

void Responser::construct_udp_header(const uint32_t & src_ip, const uint32_t & des_ip, const uint16_t & src_port, const uint16_t & des_port, const int response_length, uint8_t * buffer)
{
	udphdr *udpHeader = (udphdr *)buffer;
	udpHeader->dest = des_port;
	udpHeader->source = src_port;
	udpHeader->len = htons(response_length + sizeof(udphdr));
	udpHeader->check = 0;


	//uint8_t *forCheckSum = new uint8_t[(sizeof(UDP_PSD_Header) + sizeof(udphdr) + dnsLength + 1) / 2 * 2]();
	uint8_t *forCheckSum = buffer - sizeof(UDP_PSD_Header);
	UDP_PSD_Header *udp_PSD_Header = (UDP_PSD_Header *)forCheckSum;
	udp_PSD_Header->des = des_ip;
	udp_PSD_Header->src = src_ip;
	udp_PSD_Header->mbz = 0;
	udp_PSD_Header->ptcl = 17;
	udp_PSD_Header->len = htons(response_length + sizeof(udphdr));
	memcpy(forCheckSum + sizeof(UDP_PSD_Header), buffer, response_length + sizeof(udphdr));
	udpHeader->check = csum((uint16_t *)forCheckSum, (sizeof(UDP_PSD_Header) + sizeof(udphdr) + response_length + 1) / 2);
}




void Responser::construct_response_for_rcode0(const uint8_t * request, int request_length, const uint32_t ip, uint8_t * response, int & response_length)
{
	DNS_Head *dns_head = (DNS_Head *)response;
	uint16_t flag = ntohs(dns_head->FLAGS);
	flag |= 0x8400;//QR填1，AA填1，其他与请求报文一致
	dns_head->FLAGS = htons(flag);

	dns_head->ANCOUNT = htons(1);

	//开始构造Question部分
	int query_length = 0;
	while (*(request + 12 + query_length++));//域名部分字节长度为query_length
	query_length += 4;//加上CLASS 和TYPE的长度
	response_length = query_length + 12;
	memcpy(response + 12, request + 12, query_length);
	//Question部分构造完毕

	//开始构件answer rr
	DNS_Answer *dns_answer = (DNS_Answer *)(response + response_length);
	dns_answer->domain_ptr = htons(0xC00C);//前两位为1 偏移地址为12
	dns_answer->classs = htons(1);
	dns_answer->type = htons(1);
	dns_answer->ttl = htons(0x6666);
	dns_answer->rdata_length = htons(0x0004);
	dns_answer->rdata = ip;
	response_length += sizeof(DNS_Answer);
	//answer rr构造完毕


	//剩余部分

	if (12 + query_length == request_length)
	{
		return;
	}
	memcpy(response + response_length, request + 12 + query_length, 11);//拷贝additional rr
	response_length += 11;
}
/*
@summary
@param
@return
*/
void Responser::construct_response_for_rcode1(const uint8_t * request, int request_length, uint8_t * response, int & response_length)
{
	uint16_t flag = ntohs(((DNS_Head *)response)->FLAGS);
	flag |= 0x0001;//RCODE=1
	((DNS_Head *)response)->FLAGS = htons(flag);
	((DNS_Head *)response)->QDCOUNT = 0;
	((DNS_Head *)response)->ANCOUNT = 0;
	((DNS_Head *)response)->ARCOUNT = 0;
	((DNS_Head *)response)->NSCOUNT = 0;
	response_length = 12;
}
/*
@summary
@param
@return
*/
void Responser::construct_response_for_rcode2(const uint8_t * request, int request_length, uint8_t * response, int & response_length)
{
	uint16_t flag = ntohs(((DNS_Head *)response)->FLAGS);
	flag |= 0x0002;//RCODE=2
	((DNS_Head *)response)->FLAGS = htons(flag);

	memcpy(response + 12, request + 12, request_length-12);
	response_length = request_length;
}

uint16_t Responser::csum(uint16_t * buf, int nwords)
{
	unsigned long sum;
	for (sum = 0; nwords > 0; nwords--)
	{
		sum += *buf++;
	}
	sum = (sum >> 16) + (sum & 0xffff);
	sum += (sum >> 16);
	return (unsigned short)(~sum);
}

