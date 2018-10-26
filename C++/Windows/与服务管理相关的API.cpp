#include <Pdh.h>
#pragma comment(lib, "Pdh.lib")

#include <Psapi.h>
#pragma comment(lib, "Psapi.lib")
#include <iostream>
#include <string>
using namespace std;



int main()
{

	string svcName = "PostgreSQL";

	/*
	服务管理器是用于管理系统服务的管理工具。一般常用于windows系统，使用这个工具你可以启动、停止服务；设置服务是自动、手动启动或禁用；查看某个服务的相关信息；设置服务以什么用户启动等等（一般包括了超级管理员用户、管理员用户和游客用户）；设置用户注销，软件等。
	*/
	// Get a handle to the SCM database. 
	SC_HANDLE schSCManager = OpenSCManager(
		NULL,                    // local computer
		NULL,                    // ServicesActive database 
		SC_MANAGER_ALL_ACCESS);  // full access rights 


								 // Get a handle to the service
	SC_HANDLE schService = OpenService(
		schSCManager,         // SCM database 
		svcName.c_str(),      // name of service 
		SERVICE_ALL_ACCESS);    // full access 

	CloseServiceHandle(schSCManager);
	schSCManager = NULL;
	SERVICE_STATUS_PROCESS ssStatus;
	DWORD dwBytesNeeded;

	/*
	https://docs.microsoft.com/zh-cn/windows/desktop/api/winsvc/nf-winsvc-queryservicestatusex
	*/
	QueryServiceStatusEx(schService, SC_STATUS_PROCESS_INFO, (LPBYTE)(&ssStatus), sizeof(SERVICE_STATUS_PROCESS), &dwBytesNeeded);

	cout << "Prosess id " << ssStatus.dwProcessId << endl;
	/*
	https://docs.microsoft.com/zh-cn/windows/desktop/api/processthreadsapi/nf-processthreadsapi-openprocess
	*/
	HANDLE hProcess = ::OpenProcess(PROCESS_QUERY_INFORMATION | PROCESS_VM_READ, FALSE, ssStatus.dwProcessId);

	HMODULE hMod[100];
	DWORD dwNeeded;
	//EnumProcessModules(hProcess, &hMod, sizeof(hMod), &dwNeeded);//32位程序运行在64位系统上，获取64位进程的信息，会失败

	/*
	https://docs.microsoft.com/zh-cn/windows/desktop/api/psapi/nf-psapi-enumprocessmodulesex
	*/
	EnumProcessModulesEx(hProcess, hMod, sizeof(hMod)*100 ,&dwNeeded, LIST_MODULES_64BIT);
	cout << dwNeeded/sizeof(HMODULE) << endl;
	char szProcessName[MAX_PATH];
	//获取进程的所有模块名
	for (int i = 0; i < (dwNeeded / sizeof(HMODULE)); i++)
	{
		GetModuleBaseName(hProcess, hMod[i], szProcessName, sizeof(szProcessName) / sizeof(char));
		cout << szProcessName << endl;
	}
	

	//if (QueryFullProcessImageName(hProcess, 1, szProcessName, ((PDWORD)(sizeof(szProcessName) / sizeof(char)))) != 0)
	//{
	//	cout << szProcessName << endl;

	//}

	
}