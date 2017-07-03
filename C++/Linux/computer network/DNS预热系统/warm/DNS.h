#pragma once
#include<iostream>
namespace DNS
{
	//DNSͷ��
	struct Header
	{
		unsigned short ID;//DNS��ID
		unsigned short FLAGS;
		unsigned short QDCOUNT;//������е������¼��
		unsigned short ANCOUNT;//�ش��¼��
		unsigned short NSCOUNT;//��Ȩ��¼��
		unsigned short ARCOUNT;//���Ӽ�¼��

	};

	struct DNSinfo
	{
		std::string domainName;
		std::string Type;
		unsigned	char *Question;
		int length;
	};

	struct DNSPocket
	{
		unsigned	char *data;
		unsigned int length;
	};

	//����������ʱδ��*******************************************************************************************************8
	enum
	{
		DNS_DOMAIN_MAX_SIZE = 256//��ȫ�޶����������ߴ磨����\0��
	};


	struct Question
	{
		char domain[DNS_DOMAIN_MAX_SIZE];
		unsigned QTYPE;//��ѯ��Э������
		unsigned QCLASS;//��ѯ����

	};

	struct Answer
	{
		unsigned short NAME;//��Դ��¼����������
		unsigned short TYPE;//��ʾDNSЭ�������
		unsigned short CLASS;//��ʾRDATA����
		unsigned short TTL;//��ʾ��Դ��¼���Ի����ʱ��
		unsigned short RDLENGTH;//��ʾRDATA�ĳ���
		unsigned short RDATA;//�������ַ�������ʾ��¼
	};


	struct Authority
	{
		unsigned short NAME;//��Դ��¼����������
		unsigned short TYPE;//��ʾDNSЭ�������
		unsigned short CLASS;//��ʾRDATA����
		unsigned short TTL;//��ʾ��Դ��¼���Ի����ʱ��
		unsigned short RDLENGTH;//��ʾRDATA�ĳ���
		unsigned short RDATA;//�������ַ�������ʾ��¼


	};
	struct Additional
	{
		unsigned short NAME;//��Դ��¼����������
		unsigned short TYPE;//��ʾDNSЭ�������
		unsigned short CLASS;//��ʾRDATA����
		unsigned short TTL;//��ʾ��Դ��¼���Ի����ʱ��
		unsigned short RDLENGTH;//��ʾRDATA�ĳ���
		unsigned short RDATA;//�������ַ�������ʾ��¼

	};


}