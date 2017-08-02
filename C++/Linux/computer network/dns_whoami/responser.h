
#ifndef DNS_WHOAMI_RESPONSER_H
#define DNS_WHOAMI_RESPONSER_H

#include<inttypes.h>
#include<memory.h>

#include<arpa/inet.h>
#include<netinet/udp.h>

#include<string>
#include"dns_head.h"

class Responser
{
public:
	
	void construct_response(const uint8_t * request, int request_length, int rcode, const uint32_t ip, uint8_t * response, int &response_length);
	void construct_udp_header(const uint32_t &src_ip,const uint32_t &des_ip,const uint16_t& src_port,const uint16_t &des_port,const int response_length,uint8_t *buffer);


private:
	
	void construct_response_for_rcode0(const uint8_t * request, int request_length, const  uint32_t ip, uint8_t * response, int &response_length);
	void construct_response_for_rcode1(const uint8_t * request, int request_length, uint8_t * response, int &response_length);
	void construct_response_for_rcode2(const uint8_t * request, int request_length, uint8_t * response, int &response_length);
	uint16_t csum(uint16_t * buf, int nwords);

};
//UDP数据报的伪头部
struct UDP_PSD_Header
{
	u_int32_t src;
	u_int32_t des;
	u_int8_t  mbz;
	u_int8_t ptcl;
	u_int16_t len;
};

#endif // !DNS_WHOAMI_RESPONSER_H

