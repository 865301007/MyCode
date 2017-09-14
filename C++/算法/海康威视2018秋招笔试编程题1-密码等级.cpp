/*

海康威视 2018校招 应用软件开发工程师（C/C++）笔试题在线考试
编程题 | 10.0分 1/2
密码等级
时间限制：C/C++语言 3000MS；其他语言 5000MS
内存限制：C/C++语言 65536KB；其他语言 589824KB
题目描述：
用户的密码分为4个等级，具体定义如下：
等级0：密码小于8位/密码与用户名一样或相反/只包含4类字符中的任意一类
等级1：大于等于8位包含两类字符，且组合为(数字+小写字母)或(数字+大写字母)
等级2：大于等于8位包含两类字符，且组合不能为(数字+小写字母)和(数字+大写字母)
等级3：大于等于8位包含三类以上字符
其中4类字符为：数字，小写字母，大写字母，特殊字符(除了数字，小写字母，大写字母外的字符)
输入
第一行输入用户名 密码，用户和密码用空格分开(密码中不会包含空格)
输出
输出具体密码强度

样例输入
admin 112233
样例输出
0

*/

#include <iostream>
#include <algorithm>
#include <string>
#include<cmath>
#include<vector>
#include<map>
#include<cctype>
#include<queue>
#include<stack>
#include<math.h>
using namespace std;

int class_count(const string &s)
{
	int flag = 0;
	for (char c : s)
	{
		if (isdigit(c))
		{
			flag |= 0x1;
			continue;
		}

		if (islower(c))
		{
			flag |= 0x2;
			continue;
		}
		if (isupper(c))
		{
			flag |= 0x4;
			continue;
		}
		flag |= 0x8;
	}
	return flag;
}

bool is_match(string password, string username)
{
	if (password == username) return true;
	reverse(username.begin(), username.end());
	return password == username;
}
int main()
{
	freopen("input.txt", "r", stdin);
	string username, password;
	while (cin>>username>>password)
	{

		int flag = class_count(password);
		
		if (flag == 1 || flag == 2 || flag == 4 || flag == 8 || password.size() < 8 || is_match(username,password))
		{
			cout << 0 << endl;
			continue;
		}

		if (password.size() >= 8 && (flag == 3 || flag == 5))
		{
			cout << 1 << endl;
			continue;
		}

		if (password.size() >= 8 && (flag == 9 || flag == 6 || flag == 10 || flag == 12))
		{
			cout << 2 << endl;
			continue;
		}

		if (password.size() >= 8 && (flag >= 7))
		{
			cout << 3 << endl;
			continue;
		}



	}
	
}
