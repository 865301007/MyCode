#pragma once
#include<vector>
#include<string>
#include<stdlib.h>
#include<map>
#include"IPInfo.h"
#include"DNS.h"
class FileResolver
{
private:
	std::string DNSFilename;
	std::string IPFilename;
	bool toIPsrc(std::string);
	bool toIPdes(std::string);
	bool toDomainName(std::string, char *domainName);//��string���͵�����ת��Ϊ�������ݰ�
	bool toDomainType(std::string, unsigned short &domainType);//��string����������ӳ��Ϊ������������
	bool initDomainTypeMap();//��ʼ������ӳ��
	std::map<std::string, unsigned short> domainTypeMap;
public:
	FileResolver();
	~FileResolver();
	FileResolver(std::string DNSFilenameArg, std::string IPFilenameArg);
	std::vector<DNS::DNSinfo> getDNSInfos();
	std::vector<std::string> split(std::string str, std::string pattern);//�ַ����ָ�
	std::vector<IPInfo> getIPInfos();

private:

};

