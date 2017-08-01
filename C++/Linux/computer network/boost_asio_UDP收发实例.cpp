
#include<inttypes.h>

#include <ctime>
#include <iostream>
#include <string>

#include <boost/asio.hpp>

#include"responser.h"
#include"checker.h"

using boost::asio::ip::udp;

int main()
{
	

	try
	{
		boost::asio::io_service io_service;

		udp::socket socket(io_service, udp::endpoint(udp::v4(), 53));
		uint8_t request[1024] = { 0 };
		uint8_t response[1024] = { 0 };
		int request_length = 0;
		int response_length = 0;
		udp::endpoint remote_endpoint;
		Checker checker;
		Responser responser;
		boost::system::error_code error;

		int rcode = -1;
		for (;;)
		{
			request_length = socket.receive_from(boost::asio::buffer(request), remote_endpoint, 0, error);

			if (error && error != boost::asio::error::message_size)
				throw boost::system::system_error(error);

			if ((rcode = checker.check_request(request, request_length)) == -1)
			{
				continue;
			}
			
			std::string ip = remote_endpoint.address().to_string();
			responser.construct_response(request, request_length, rcode, ip, response, response_length);

			socket.send_to(boost::asio::buffer(response, response_length), remote_endpoint, 0, error);
		}
	}
	catch (const std::exception& e)
	{
		std::cerr << e.what() << std::endl;
	}

	return 0;
}
