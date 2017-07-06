#include <stdio.h>
#include <stdio.h>
#include <winsock2.h>
#include "FileHelper.h"
#pragma comment(lib,"ws2_32.lib")

int main(int argc, char* argv[])
{
	//��ʼ��WSA
	WORD sockVersion = MAKEWORD(2,2);
	WSADATA wsaData;
	FileHelper fh;
	if(WSAStartup(sockVersion, &wsaData)!=0)
	{
		return 0;
	}

	//�����׽���
	SOCKET slisten = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
	if(slisten == INVALID_SOCKET)
	{
		printf("socket error !");
		return 0;
	}

	//��IP�Ͷ˿�
	sockaddr_in sin;
	sin.sin_family = AF_INET;
	sin.sin_port = htons(8888);
	sin.sin_addr.S_un.S_addr = INADDR_ANY; 
	if(bind(slisten, (LPSOCKADDR)&sin, sizeof(sin)) == SOCKET_ERROR)
	{
		printf("bind error !");
	}

	//��ʼ����
	if(listen(slisten, 5) == SOCKET_ERROR)
	{
		printf("listen error !");
		return 0;
	}

	//ѭ����������
	SOCKET sClient;
	sockaddr_in remoteAddr;
	int nAddrlen = sizeof(remoteAddr);
	char revData[BUFSIZ]; 
	while (true)
	{
		printf("�ȴ�����...\n");
		sClient = accept(slisten, (SOCKADDR *)&remoteAddr, &nAddrlen);
		if(sClient == INVALID_SOCKET)
		{
			printf("accept error !");
			continue;
		}
		printf("���ܵ�һ�����ӣ�%s \r\n", inet_ntoa(remoteAddr.sin_addr));
		FILE *f=fh.createFile(inet_ntoa(remoteAddr.sin_addr));
		int ret=0;
		while ((ret=recv(sClient, revData, BUFSIZ, 0))>0)
		{
			printf("%d\n",ret);
			printf(revData);
			fwrite(revData,1,ret,f);
			//revData[ret] = 0x00;
			//printf(revData);
			//��������
			char * sendData = "��ã�TCP�ͻ��ˣ�\n";
			send(sClient, sendData, strlen(sendData), 0);
		}
		fclose(f);




		closesocket(sClient);
	}

	closesocket(slisten);
	WSACleanup();
	return 0;
}