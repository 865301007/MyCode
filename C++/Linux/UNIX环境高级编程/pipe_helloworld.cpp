
//pipe hellowordl
//gcc 4.8  centos 7.3.1611
#include<unistd.h>//for pipe

#include<iostream>


#define MAXLINE 100

using namespace std;
int  main()
{
	int n;
	int fd[2];
	pid_t pid;
	char line[MAXLINE];

	if (pipe(fd) < 0)
		cout << "pipe error!" << endl;
	if ((pid = fork()) < 0)
	{
		cout << "fork error!" << endl;
		exit(-1);
	}
	if (pid > 0)
	{
		close(fd[0]);
		write(fd[1], "hello world\n", 12);
	}
	else
	{
		close(fd[1]);
		n = read(fd[0], line, MAXLINE);
		write(STDOUT_FILENO, line, n);
		//cout << n << endl;
	}
	
}