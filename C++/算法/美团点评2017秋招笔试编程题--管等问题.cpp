/*

美团点评 2018校招 后台开发方向试卷在线考试
编程题 | 30.0分 2/2
关灯游戏
时间限制：C/C++语言 2000MS；其他语言 4000MS
内存限制：C/C++语言 65536KB；其他语言 589824KB
题目描述：
在 Alice 生日的那天，Bob 送给了她 n 个灯泡。他们决定用这些灯泡玩一个游戏：他们把这些灯泡从左往右排成一行，在初始时，有些灯泡是点亮的，有些灯泡是熄灭的。接下来，他们轮流进行操作，Alice 首先操作。在每一次操作中，轮到操作的人需要选择一个点亮的灯泡，然后把它以及它右边的所有灯泡的状态进行一次改变，即把点亮的灯泡熄灭，把熄灭的灯泡点亮。如果在某一个人操作完之后，所有的灯泡都变成了熄灭状态，那么那个人就赢得了游戏。Alice 和 Bob 都想赢得游戏，在他们都足够聪明的情况下，最后谁会赢呢？
输入
第一行包含一个整数n，表示灯泡的个数。1≤n≤105
第二行包含n个 0 或 1，表示初始时灯泡的状态，0 表示熄灭，1 表示点亮。
输出
如果最后 Alice 能赢，输出 Alice，或则输出 Bob。

样例输入
3
0 1 1
样例输出
Alice

Hint
Input Sample 2
5
1 1 1 0 0

Output Sample 2
Bob
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


int main()
{
	//freopen("input.txt", "r", stdin);
	int n;
	while (cin>>n)
	{
        int last=0;
		for (int i = 0; i < n; i++)
		{
			cin >> last;
		}

		if (last == 1)
		{
			cout << "Alice" << endl;
		}
		else
		{
			cout << "Bob" << endl;
		}

	}
	
}
