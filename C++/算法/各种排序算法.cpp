//#include "header.h"	//AnycodeX includes the header.h by default, needn't cancle the notation.
#include <iostream>
#include <algorithm>
#include <string>
#include<cmath>
#include<vector>
#include<map>
#include<cctype>

using namespace std;

//冒泡排序，每次比较相邻的两个元素，如果顺序错误便交换之
void bubble_sort(vector<int> &v)
{
	for (int i = 0; i < v.size() - 1; i++)
	{
		for (int j = 0; j < v.size() - 1 - i; j++)
		{
			if (v[j] > v[j + 1])
			{
				swap(v[j], v[j + 1]);
			}
		}
	}
}

//冒泡排序改进版，设置标志位，一旦一趟比较没有需要交换元素的操作则表明排序成功
//可以减少原本有序的序列的比较次数
void bubble_sort_s(vector<int> &v)
{
	for (int i = 0; i < v.size() - 1; i++)
	{
		bool flag = 1;
		for (int j = 0; j < v.size() - 1 - i; j++)
		{
			if (v[j] > v[j + 1])
			{
				swap(v[j], v[j + 1]);
				flag = 0;
			}
		}
		if (flag) break;
	}
}

//冒泡排序第二种改进，记录最后一次发生改变的位置。则此位置之后的都是已经排好序的
void bubble_sort_s2(vector<int> &v)
{
	int pos = v.size() - 1;
	int last_change = 0;
	while (pos > 0)
	{
		last_change = 0;
		for (int i = 0; i < pos; ++i)
		{
			if (v[i] > v[i + 1])
			{
				swap(v[i], v[i + 1]);
				last_change = i;
			}
		}
		pos = last_change;
	}
}

//冒泡排序第三种改进，双路并进
void bubble_sort_s3(vector<int> &v)
{
	for (int i = 0; i < v.size() - 1; i++)
	{
		for (int j = 0; j < v.size() - 1 - i; j++)
		{
			if (v[j] > v[j + 1])
			{
				swap(v[j], v[j + 1]);
			}
		}
		for (int j = v.size() - 2 - i; j > 0; --j)
		{
			if (v[j] < v[j - 1])
			{
				swap(v[j], v[j - 1]);
			}
		}
	}


}


//快速排序算法
/*
有几种改进办法：
1.在递归小于一定程度的时候，采用插入排序算法
2.选取中位数作为key值，对于顺序统计来说会有较大的提升
3.采用随机数作为key值，并不会减少平均复杂度
4.采用中间和两端的中位数作为key值，平均减少了14%左右
*/
void quick_sort(vector<int> &v, int left, int right)
{
	if (left >= right) return;
	int temp = v[left];
	int l = left;
	int r = right;
	while (left < right)
	{
		while (left < right&&v[right] >= temp) right--;
		v[left] = v[right];
		while (left < right&&v[left] <= temp)left++;
		v[right] = v[left];
	}
	v[left] = temp;
	quick_sort(v, l, left - 1);
	quick_sort(v, left + 1, r);
}

//插入排序
/*
改进方法
1.二分查找插入
*/
void insert_sort(vector<int> &v)
{
	for (int i = 1; i < v.size(); i++)
	{
		int value = v[i];
		for (int j = i; j > 0; j--)
		{
			if (v[j - 1] > value)
			{
				v[j] = v[j - 1];
			}
			else
			{
				v[j] = value;
				break;
			}
		}
	}
}

//选择排序
/*
改进方法
1.二元选择排序，每次遍历选出最大值和最小值
*/
void select_sort(vector<int> &v)
{
	for (int i = 0; i < v.size(); i++)
	{
		int min = v[i];
		int pos = i;
		for (int j = i + 1; j < v.size(); j++)
		{
			if (min > v[j])
			{
				min = v[j];
				pos = j;
			}
		}
		swap(v[i], v[pos]);
	}
}

void print(const vector<int> v)
{
	for (size_t i = 0; i < v.size(); i++)
	{
		cout << v[i] << ' ';
	}
	cout << endl;
}
int main()
{
	int my_array[] = { 0,3,5,7,1,8,6,4,2,9 };
	vector<int> v(my_array, my_array + 10);
	select_sort(v);

	print(v);

}