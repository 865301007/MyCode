
#ifndef DNS_WHOAMI_DNS_HEAD_H_
#define DNS_WHOAMI_DNS_HEAD_H_

 //DNS头部
struct DNS_Head
{
	unsigned short ID;//DNS的ID
	unsigned short FLAGS;
	unsigned short QDCOUNT;//请求段中的问题记录数
	unsigned short ANCOUNT;//回答记录数
	unsigned short NSCOUNT;//授权记录数
	unsigned short ARCOUNT;//附加记录数

};
#pragma pack(push) //保存对齐状态
#pragma pack(2)// 设定为4字节对齐
struct DNS_Answer
{
	uint16_t domain_ptr;
	uint16_t type;
	uint16_t classs;
	uint32_t ttl;
	uint16_t rdata_length;
	uint32_t rdata;
};
#pragma pack(pop)// 恢复对齐状态
#endif // !DNS_WHOAMI_DNS_HEADER_H_
