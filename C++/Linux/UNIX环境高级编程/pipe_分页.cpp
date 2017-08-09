
//pipe hellowordl
//gcc 4.8  centos 7.3.1611
/*
每次一页地显示已产生的输出
*/
#include<unistd.h>//for pipe
#include<string.h>	/*for strlen*/
#include<sys/wait.h>	/*for waitpid*/

#include<iostream>



#define DEF_PAGER  "/bin/more"  /*默认的分页程序*/
#define MAXLINE 20
using namespace std;
int  main(int argc, char *argv[])
{
	int n;
	int fd[2];

	pid_t pid;
	char *pager, *argv0;
	char line[MAXLINE];
	FILE * fp;

	if (argc != 2)
	{
		cout << "usage:a.out <pathname>" << endl;
		exit(-1);
	}
	if ((fp = fopen(argv[1], "r")) == NULL)
	{
		cout << "can't open " << argv[1] << endl;
		exit(-1);
	}

	if (pipe(fd) < 0)
	{
		cout << "pipe error" << endl;
		exit(-1);
	}

	if ((pid = fork()) < 0)
	{
		cout << "fork error" << endl;
		exit(-1);
	}
	if (pid > 0)
	{
		close(fd[0]);
		while (fgets(line, MAXLINE, fp) != NULL)/*从文件中读取字符，每次一行，每次最多读maxline-1个字符*/
		{
			n = strlen(line);
			if (write(fd[1], line, n) != n)
			{
				cout << "write error" << endl;
			}

		}

		if (ferror(fp))//对同一个文件，每一次调用输入输出函数，均会产生一个新的ferror值
		{
			cout << "fgets error" << endl;
		}

		close(fd[1]);
		if (waitpid(pid, NULL, 0) < 0)/*暂时停止当前进程，等待别的进程*/
		{
			cout << "waitpid error" << endl;
		}
		exit(0);
	}
	else   /*child*/
	{
		close(fd[1]);
		if (fd[0] != STDIN_FILENO)/* STDIN_FILENO是一个打开文件句柄，主要用于系统调用，是标准输入输出描述符*/
		{
			if (dup2(fd[0], STDIN_FILENO) != STDIN_FILENO)/* dup2 复制文件描述符*/
			{
				cout << "dup2 error to stdin" << endl;
			}
			close(fd[0]);	/*don't need this after dup2*/
		}

		if ((pager = getenv("PAGER")) == NULL)/*环境变量中的值*/
		{
			pager = DEF_PAGER;
		}

		if ((argv0 == strrchr(pager, '/')) != NULL)/*查找字符在指定字符串中从左面开
												   始的最后一次出现的位置，如果成功，
												   返回该字符以及其后面的字符，如果失败，则返回 NULL*/
		{
			argv0++;
		}
		else
		{
			argv0 = pager;
		}

		if (execl(pager, argv0, (char *)0) < 0)
		{
			cout << "execl error" << endl;
		}
		exit(0);
	}
	exit(0);

}