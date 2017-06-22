
#include <iostream>  
#include<string>
#include <boost/thread/thread.hpp>  
#include<boost/program_options.hpp>
#include<boost\foreach.hpp>
void print_vm(boost::program_options::options_description &opts,boost::program_options::variables_map &vm)
{
	if (vm.size() == 0)
	{
		std::cout << opts << std::endl;
		return;
	}
	if (vm.count("help"))
	{
		std::cout << opts << std::endl;//���������Ϣ
	}
	//��������ļ�������Ϊ����ȱʡֵ�������Ǵ���
	std::cout << "find opt::" << vm["filename"].as<std::string>() << std::endl;
	if (vm.count("dir"))
	{
		std::cout << "dir toor:";
		BOOST_FOREACH(std::string str, vm["dir"].as<std::vector<std::string>>())
		{
			std::cout << str << ",";
		}
		std::cout << std::endl;
	}

	if (vm.count("depth"))
	{
		std::cout << "depth opt:" << vm["depth"].as<int>() << std::endl;
	}
}

int main(int argc,char *argv[])
{
	boost::program_options::options_description opts("demo options");
	std::string filename;
	opts.add_options()
		("help,h", "help message\n a bit of long text")//����ѡ�ʹ�ÿո�������ʽ
		//�ļ���ѡ�ֵ�ɴ洢���ⲿ��ȱʡֵ��test
		("filename,f", boost::program_options::value<std::string>(&filename)->default_value("test"), "to find a file")
		//����·��ѡ����Զ�γ��֣����Խ��ܶ���Ǻ�
		("dir,D", boost::program_options::value<std::vector<std::string>>()->multitoken(), "search dir")
		//�������ѡ�����ֵ��5������������·����ͬ
		("depth,d", boost::program_options::value<int>()->implicit_value(5), "search depth");

	boost::program_options::variables_map vm;
	try 
	{
		boost::program_options::store(boost::program_options::parse_command_line(argc, argv, opts), vm);
	}
	catch (...)
	{
		std::cout << "������δ�����ѡ��!\n" << std::endl;
		return -1;
	}
	boost::program_options::notify(vm);//��������洢�ⲿ����
	//������ɣ�ʵ��ѡ����߼�
	print_vm(opts, vm);
	
}