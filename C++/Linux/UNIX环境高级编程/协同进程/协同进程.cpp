/*当一个过滤程序既产生某个过滤程序的输入，又读取该过滤程序的输出时，它就变成了协同进程*/


/*
父进程读取标准输入，并将标准输入输入到fd1[1]中
子进程将fd1[0]视为标准输入，并将标准输入传递到add2程序中
add2程序产生结果，即标准输出
子进程将fd2[1]视为标准输出，add2产生的结果输出到fd2[1]
父进程从fd2[0]中读取结果并输出到控制台
*/


#include<unistd.h>
#include<stdio.h>
#include<signal.h>
#include<string.h>
#include<iostream>

using namespace std;
#define MAXLINE 5

/*如果程序在等待输入的时候杀死了add2协同进程，然后又输入两个数，那么程序对没有
读进程的管道进行写操作时，会调用信号处理程序*/
static void sig_pipe(int)/* our singnal handler*/
{
	printf("SIGPIPE caught\n");
	exit(1);
}

int main()
{
	int n, fd1[2], fd2[2];
	pid_t pid;
	char line[MAXLINE];

	if (signal(SIGPIPE,sig_pipe)==SIG_ERR)
	{
		cout << "signal error" << endl;
	}
	if (pipe(fd1) < 0 || pipe(fd2) < 0)
		cout << "pipe error" << endl;

	if ((pid = fork()) < 0)
	{
		cout << "fork error" << endl;
	}
	if (pid > 0)
	{
		close(fd1[0]);
		close(fd2[1]);

		while (fgets(line,MAXLINE,stdin)!=NULL)
		{
			n = strlen(line);
			if (write(fd1[1], line, n) != n)
			{
				cout << "write error" << endl;
			}
			if ((n = read(fd2[0], line, MAXLINE))<0)
			{
				cout << "read error from pipe" << endl;
			}
			if (n == 0)
			{
				cout << "child close pipe" << endl;
			}
			line[n] = 0;
			if (fputs(line, stdout) == EOF)
			{
				cout << "fputs error" << endl;
			}
		}

	}
	else
	{
		close(fd1[1]);
		close(fd2[0]);

		if (fd1[0] != STDIN_FILENO)
		{
			if (dup2(fd1[0], STDIN_FILENO) != STDIN_FILENO)
			{
				cout << "dup2 error" << endl;
			}
			close(fd1[0]);
		}

		if (fd2[1] != STDOUT_FILENO)
		{
			if (dup2(fd2[1], STDOUT_FILENO) != STDOUT_FILENO)
			{
				cout << "dup2 error" << endl;
			}
		}
		if (execl("/root/add2", "add2", (char *)0) < 0)
			cout << "execl error" << endl;
	}
}

