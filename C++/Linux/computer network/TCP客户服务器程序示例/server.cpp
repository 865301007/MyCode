/* TCP回射服务端程序*/


#include<unistd.h>//for read write
#include<netinet/in.h> //for sockaddr_in
#include<arpa/inet.h> //for inet_pton
#include<stdio.h>  //for stdion stdout
#include<errno.h>  //for  errno
#include<string.h>
#include<iostream>

using namespace std;
#define MAXLINE 100
int SERVER_PORT = 21;
int LISTENQ = 20;

void str_echo(int sockfd)
{
	ssize_t n;
	char buf[MAXLINE];

again:
	while ((n=read(sockfd,buf,MAXLINE))>0)
	{
		write(sockfd, buf, n);

		if (n < 0 && errno == EINTR)
			goto again;
		else if(n<0)
		{
			cout << "error" << endl;
		}
	}
}

int main()
{
	int listenfd, connfd;
	pid_t childpid;
	socklen_t client;
	sockaddr_in cliaddr, servaddr;
	listenfd = socket(AF_INET, SOCK_STREAM, 0);
	bzero(&servaddr, sizeof(servaddr));
	servaddr.sin_family = AF_INET;
	servaddr.sin_addr.s_addr = htonl(INADDR_ANY);
	servaddr.sin_port = htons(SERVER_PORT);
	bind(listenfd, (sockaddr *)&servaddr, sizeof(servaddr));
	listen(listenfd, LISTENQ);
	while (true)
	{
		client = sizeof(cliaddr);
		connfd = accept(listenfd, (sockaddr *)&servaddr, &client);
		if ((childpid = fork()) == 0)
		{
			close(listenfd);
			str_echo(connfd);
		//	cout<<"child has been closed!"<<endl;
			exit(0);
		//子进程结束后，依然处于僵死状态，占用系统资源	
		}
		close(connfd);//如果不关闭，则会一直处于close_wait状态
	}
	return 0;
}
