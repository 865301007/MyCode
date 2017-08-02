#include "checker.h"






/*
@summary 检查请求包
@param request 指向请求包的指针
@param request_length 请求包的长度
@return 返回对应的rcode,错误包返回-1
*/
int Checker::check_request(const uint8_t * request, int& request_length)
{

	if (request_length < 12)//请求报文少于12字节,不要响应
	{
		return -1;
	}

	int rcode = 0;

	rcode = this->check_head(request);//检验头部
	if (rcode != 0)
	{
		return rcode;
	}

	int domain_length = 0;
	rcode = this->check_domain(request + 12, request_length - 12 - 4, domain_length); //检验域名
	if (rcode != 0)
	{
		return rcode;
	}
	uint16_t TYPE = htons(*((uint16_t *)(request + 12 + domain_length)));
	uint16_t CLASS = htons(*((uint16_t *)(request + 12 + domain_length + 2)));
	if (CLASS != 1)
	{
		return -1;//请求报文中CLASS不是1，不要响应
	}


	int addition_length = 0;
	if (((DNS_Head *)(request))->ARCOUNT)
	{
		rcode = this->check_addition(request + 12 + domain_length + 4, request_length - 12 - domain_length - 4, addition_length);
		if (rcode != 0)
		{
			return rcode;
		}
	}

	if (addition_length + domain_length + 4 + 12 < request_length)//报文结束后还有额外的数据
	{
		return -1;
	}

	if (TYPE != 1)//请求类型不是1，其他字段正确
	{
		return 2;
	}

	return 0;
}
/*
@summary 检查请求包的头部
@paramr  指向头部的指针
@return  返回rcode
*/
int Checker::check_head(const uint8_t * request)
{
	DNS_Head *dns_head = (DNS_Head *)request;
	uint16_t flag = ntohs(dns_head->FLAGS);
	if (flag & 0x8000)//QR 写成了1，不要响应
	{
		return -1;
	}
	if (ntohs(dns_head->QDCOUNT) != 1)//Total Questions 不为1,不做响应
	{
		return -1;
	}
	if (ntohs(dns_head->ANCOUNT) != 0)//Total Answer RRs不为0，不做响应
	{
		return -1;
	}
	if (ntohs(dns_head->NSCOUNT) != 0)//Total Authority RRs不为0，不做响应
	{
		return -1;
	}
	if (ntohs(dns_head->ARCOUNT) != 0 && ntohs(dns_head->ARCOUNT) != 1)//Total Additional RRs不为0也不为1，不做响应
	{
		return -1;
	}
	return 0;
}

/*
@summary 检查域名
@param domain为指向域名的指针
@param max_length 请求包中允许存放域名的最大长度
@param domain_length 将要计算的域名的长度
@return 返回RCODE
*/
int Checker::check_domain(const uint8_t * domain, int max_length, int & domain_length)
{
	int sub_lable_length = 0;
	char c;
	char pre;
	char bac;
	while (sub_lable_length = domain[domain_length])
	{
		if (sub_lable_length > 63)//域名格式不合法
		{
			return 1;
		}
		int cur = domain_length + 1;
		domain_length += sub_lable_length + 1;
		if (domain_length >= max_length || domain_length >= 255)
		{

			return 1;//域名格式太长或者不完整
		}
		
		for (size_t i = cur; i < domain_length; i++)
		{
			c = domain[i];
			pre = domain[i - 1];
			bac = domain[i + 1];


			if (c == '-')
			{
				if (!((pre >= '0'&&pre <= '9' || pre >= 'a'&&pre <= 'z' || pre >= 'A' || pre <= 'Z') &&
					(bac >= '0'&&bac <= '9' || bac >= 'a'&&bac <= 'z' || bac >= 'A' || bac <= 'Z')))
				{

					return 1;//域名格式非法，-只能出现在节点的首位
				}
			}
			else if (!(c >= '0'&&c <= '9' || c >= 'a'&&c <= 'z' || c >= 'A' || c <= 'Z'))
			{

				return 1;//域名格式非法，只能出现字母数字或者-
			}

		}

	}
	domain_length++;//加上结尾为0

	if (domain_length > max_length || domain_length >= 255)
	{

		return 1;//域名格式太长或者不完整
	}
	return 0;
}
/*
@summary 检查附加部分
@param addition 指向附加部分的指针
@param max_length 请求包中附加部分所能允许的最大长度
@param addition_length 输出附加包的长度
@return rcode
*/
int Checker::check_addition(const uint8_t * addition, int max_length, int &addition_length)
{
	addition_length = 11;
	if (addition_length > max_length)
	{
		return 1;//报文不完整
	}
	unsigned name = addition[0];
	if (name != 0)
	{
		return -1;
	}
	unsigned type = ntohs(*(uint16_t *)(addition + 1));
	if (type != 41)
	{
		return -1;
	}
	unsigned RdataLength = (*(uint16_t *)(addition + 1 + 2 + 2 + 4));
	if (RdataLength != 0)
	{
		return -1;
	}
	return 0;
}
