#include<stdio.h>
#include <WINSOCK2.H>
#include <STDIO.H>

#pragma  comment(lib,"ws2_32.lib")

class FileHelper
{
private:
	FILE *f;
public:
	FILE * selectfile()
	{
		printf("������Ҫ���͵��ļ���\n");
		char name[100];
		scanf("%s",name);
		getchar();
		
		if (f=fopen(name,"r"))
		{
			printf("�ļ��򿪳ɹ�\n");
			return f;
		}
		else
		{
			printf("�ļ������ڣ�����������\n");
			return selectfile();
		}
	}

	FILE * createFile(char *name)
	{
		if (f=fopen(name,"w"))
		{
			printf("�ļ�%s�����ɹ�\n");
		}
		else
		{
			printf("�ļ�����ʧ��\n");
		}
		return f;
	}

	
};