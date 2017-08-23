/*
阿里巴巴2018年秋季招聘研发工程师编程测试题
1. 猎人把一对兔子婴儿(一公一母称为一对)放到一个荒岛上，两年之后，它们生下一对小兔，之后开始每年都会生下一对小兔。生下的小兔又会以同样的方式继续繁殖。
2. 兔子的寿命都是x(x>=3)年，并且生命的最后一年不繁殖。
3. 如果岛上的兔子多于10对，那么猎人会每年在兔子们完成繁殖或者仙逝之后，从岛上带走两对最老的兔子。
请问y年(y>=3)后荒岛上所有的兔子加起来多少岁?(注意, 在条件3执行完之后)

输入: 从命令行输入两行整数，第一行是x，第二行是y
输出: y年后荒岛上所有的兔子岁数的总和
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
using namespace std;

void jian1(vector<int> &v, int x)
{
	int new_count = 0;
	int dead_count = 0;
	for (int i = 0; i < v.size(); i++)
	{
		v[i]--;
		if (v[i] == 0)
		{
			dead_count++;
			continue;
		}
		if (x - v[i] >= 2)
		{
			new_count++;
		}
	}
	v.assign(v.begin() + dead_count, v.end());
	for (int i = 0; i < new_count; i++)
	{
		v.push_back(x);
	}
	if (v.size() > 10)
	{
		v.assign(v.begin() + 2, v.end());
	}
}

int main()
{
	//freopen("input.txt", "r", stdin);
	int x, y;
	while (cin >> x >> y)
	{
		vector<int> v;
		v.push_back(x);
		for (int i = 1; i <= y; i++)
			jian1(v, x);

		int result = 0;

		for (int i = 0; i < v.size(); i++)
		{
			//cout <<x- v[i] << ' ';
			result += (x - v[i]);
		}

		//cout << endl;

		cout << result * 2 << endl;
	}
}