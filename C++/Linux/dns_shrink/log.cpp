#include "log.h"

void LogError(std::string message)
{
	std::cout << "[ERROR]   :" << message << std::endl;
}

void LogInfo(std::string message)
{
	std::cout << "[INFO]    :" << message << std::endl;
}

void LogWarn(std::string message)
{
	std::cout << "[WARNING] :" << message << std::endl;
}

void LogDebug(std::string message)
{
	std::cout << "[DEBUG]  :" << message << std::endl;
}


