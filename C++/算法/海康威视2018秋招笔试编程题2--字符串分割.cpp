/*

海康威视 2018校招 应用软件开发工程师（C/C++）笔试题在线考试
编程题 | 10.0分 2/2
字符串分隔
时间限制：C/C++语言 3000MS；其他语言 5000MS
内存限制：C/C++语言 65536KB；其他语言 589824KB
题目描述：
给定一个字符串及分隔符，字符串和分隔符之间以空格分开(分隔符中不会包含空格)要求根据该分隔符进行拆分，每个小的字符串输出为一行。
输入
第一行输入原始字符串 分隔符，原始字符串和分隔符空格分开(分隔符中不会包含空格)
输出
输出分隔好后的每个字符串，每个字符串以换行符分开

样例输入
abc,def,ghi,jkl ,
样例输出
abc
def
ghi
jkl

温馨提示
请尽量在全场考试结束10分钟前调试程序，否则由于密集排队提交，可能查询不到编译结果
点击“调试”亦可保存代码
编程题可以使用本地编译器，此页面不记录跳出次数
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

std::vector<std::string> split(std::string str, std::string pattern)
{
	std::string::size_type pos;
	std::vector<std::string> result;
	str += pattern;
	int size = str.size();

	for (int i = 0; i<size; i++)
	{
		pos = str.find(pattern, i);
		if (pos<size)
		{
			std::string s = str.substr(i, pos - i);
			result.push_back(s);
			i = pos + pattern.size() - 1;
		}
	}
	return result;
}
int main()
{
	freopen("input.txt", "r", stdin);
	string s1, s2;
	while (cin>>s1>>s2)
	{
		vector<string> result = split(s1, s2);
		for (string s : result)
		{
			cout << s << endl;
		}

	}
	
}
