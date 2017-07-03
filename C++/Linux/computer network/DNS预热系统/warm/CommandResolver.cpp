#include<iostream>
#include<boost/program_options.hpp>
#include<fstream>
#include "CommandResolver.h"

bool CommandResolver::CheckTheParament()
{
	
	std::cout << "Resovleing the Commmand....." << std::endl;
	std::ifstream dns(DNSFileName.c_str());
	if (!dns.is_open())
	{
		std::cout << "The file of domain is not exist " << std::endl;
		exit(-1);
		return false;
	}
	std::ifstream ip(IPFileName.c_str());
	if (!ip.is_open())
	{
		std::cout << "The file of address is not exist " << std::endl;
		exit(-1);
		return false;
	}
	dns.close();
	ip.close();
	return true;
}

CommandResolver::CommandResolver()
{
}

CommandResolver::~CommandResolver()
{
}

CommandResolver::CommandResolver(int & c, char * v[]) :argc(c)
{
	argv = v;
	rate = 0;

}

std::string CommandResolver::getDNSFileName()
{
	return DNSFileName;
}

std::string CommandResolver::getIPFileName()
{
	return IPFileName;
}

int CommandResolver::getRate()
{
	return rate;
}

bool CommandResolver::ResovleCommand()
{
	boost::program_options::options_description opts("warm options");
	opts.add_options()
		("help,h", "help message\n a bit of long text")//����ѡ�ʹ�ÿո�������ʽ
		("domain,d", boost::program_options::value<std::string>(&DNSFileName), "to specify domain name file")
		//����·��ѡ����Զ�γ��֣����Խ��ܶ���Ǻ�
		("address,a", boost::program_options::value<std::string>(&IPFileName), "to specify address file")
		("rate,r", boost::program_options::value<int>(&rate), "to specify the send rate");
	boost::program_options::variables_map vm;
	try
	{
		boost::program_options::store(boost::program_options::parse_command_line(argc, argv, opts), vm);
	}
	catch (...)
	{
		std::cout << "The undefined items are entered!" << std::endl;
		return false;
	}
	boost::program_options::notify(vm);//��������洢�ⲿ����
									   //������ɣ�ʵ��ѡ����߼�
	if (vm.size() == 0)
	{
		std::cout << opts << std::endl;
		return false;
	}
	if (vm.count("help"))
	{
		std::cout << opts << std::endl;//���������Ϣ
		return false;
	}
	if (!vm.count("help") && (DNSFileName.empty() || IPFileName.empty() || !rate))
	{
		std::cout << "Insufficient parameters!" << std::endl;
		return false;
	}
	if (rate<0)
	{
		std::cout << "rate should be negative" << std::endl;
		return false;
	}
	//std::cout << "Checking The Parament" << std::endl;
	return CheckTheParament();
}


