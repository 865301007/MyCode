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
	��������������ڹ���ϵͳ����Ĺ����ߡ�һ�㳣����windowsϵͳ��ʹ��������������������ֹͣ�������÷������Զ����ֶ���������ã��鿴ĳ������������Ϣ�����÷�����ʲô�û������ȵȣ�һ������˳�������Ա�û�������Ա�û����ο��û����������û�ע��������ȡ�
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
	//EnumProcessModules(hProcess, &hMod, sizeof(hMod), &dwNeeded);//32λ����������64λϵͳ�ϣ���ȡ64λ���̵���Ϣ����ʧ��

	/*
	https://docs.microsoft.com/zh-cn/windows/desktop/api/psapi/nf-psapi-enumprocessmodulesex
	*/
	EnumProcessModulesEx(hProcess, hMod, sizeof(hMod)*100 ,&dwNeeded, LIST_MODULES_64BIT);
	cout << dwNeeded/sizeof(HMODULE) << endl;
	char szProcessName[MAX_PATH];
	//��ȡ���̵�����ģ����
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