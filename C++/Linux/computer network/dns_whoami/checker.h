
#ifndef DNS_WHOAMI_CHECKER_H_
#define DNS_WHOAMI_CHECKER_H_

#include<inttypes.h>

#include<arpa/inet.h>

#include<iostream>
#include<iomanip>

#include"dns_head.h"
class Checker
{
public:
	int check_request(const uint8_t *request, int &request_length);

	//为测试方便，以下方法仍然设为public

	int check_head(const uint8_t *request);

	int check_domain(const uint8_t * domain, int max_length,int &domain_length);

	int check_addition(const uint8_t * addition, int max_length,int &additional_length);
private:

};

#endif // !DNS_WHOAMI_CHECKER_H_
