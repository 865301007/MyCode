#include "command_resolver.h"

bool CommandResolver::resolve_command(int argc, char * argv[])
{
	LogInfo("resolving command");
	boost::program_options::options_description opts("dns_shrink options");
	opts.add_options()
		("help,h", "help message\n a bit  of long text")
		("domain,d", boost::program_options::value<std::string>(&this->dn_file_name), "to specify domain file")
		("view,v", boost::program_options::value<std::string>(&this->cv_file_name), "to specify view file");
	boost::program_options::variables_map vm;
	try
	{
		boost::program_options::store(boost::program_options::parse_command_line(argc, argv, opts), vm);
	}
	catch (...)
	{
		LogInfo("The undefined items are enterd!");
		exit(-1);
	}

	boost::program_options::notify(vm);
	

	if (vm.size() == 0)
	{
		std::cout << opts << std::endl;
		exit(-1);
	}

	if (vm.count("help"))
	{
		std::cout << opts << std::endl;//输出帮助信息
		exit(-1);
	}
	if (!vm.count("help") && (this->cv_file_name.empty() || this->dn_file_name.empty() ))
	{
		LogError("Insufficient parameters!");
		exit(-1);
	}
	return check_parament();
}

std::string CommandResolver::get_cv_file_name()
{
	return this->cv_file_name;
}

std::string CommandResolver::get_dn_file_name()
{
	return this->dn_file_name;
}

bool CommandResolver::check_parament()
{
	LogInfo("checking the parament");
	std::ifstream domain(this->dn_file_name.c_str());
	if (!domain.is_open())//文件不存在
	{
		LogError("The file of domain is not exist");
		domain.close();
		exit(-1);
	}
	domain.close();

	std::ifstream view(this->cv_file_name.c_str());
	if (!view.is_open())
	{
		LogError("The file of view is not exist");
		domain.close();
		exit(-1);
	}
	view.close();

	return true;
}

