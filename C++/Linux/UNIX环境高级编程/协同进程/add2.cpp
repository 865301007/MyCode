#include<unistd.h>
#include<string.h>
#include<stdio.h>
#include<iostream>

using namespace std;
#define MAXLINE 5
int main()
{
	int n, int1, int2;
	char line[MAXLINE];
	while ((n=read(STDIN_FILENO,line,MAXLINE))>0)
	{
		line[n] = 0;
		if (sscanf(line, "%d%d", &int1, &int2) == 2)
		{
			sprintf(line, "%d\n", int1 + int2);
			n = strlen(line);
			if (write(STDOUT_FILENO, line, n) != n)
			{
				cout << "write error" << endl;
			}
		}
		else if (write(STDOUT_FILENO, "invalid args\n", 13) != 13)
		{
			cout << "write error" << endl;
		}
	}
}
