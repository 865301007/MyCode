

#ifndef DNS_SHRINK_COMMAND_RESOLVER_H_
#define DNS_SHRINK_COMMAND_RESOLVER_H_

#include<iostream>
#include<string>
#include<fstream>

#include<boost/program_options.hpp>


#include"log.h"

class CommandResolver
{
public:
	bool resolve_command(int argc, char *argv[]);
	std::string get_cv_file_name();
	std::string get_dn_file_name();

private:
	std::string cv_file_name;
	std::string dn_file_name;
	
	bool check_parament();
	
	
};



#endif // !DNS_SHRINK_COMMAND_RESOLVER_H_


