
/*
给定一个字符串S和有效单词的字典D，请确定可以插入到S中的最小空格数，使得最终的字符串完全由D中的有效单词组成，并输出解。
如果没有解则应该输出n/a
例如
输入
S = "ilikealibaba"
D = ["i", "like", "ali", "liba", "baba", "alibaba"]
Example Output:
输出
"i like alibaba"
解释：
字符串S可能被字典D这样拆分
"i like ali baba"
"i like alibaba"
很显然，第二个查分结果是空格数最少的解。
*/
#include <iostream>
#include<string>
#include<algorithm>
#include<functional>
#include<vector>
#include<set>
#include<sstream>
#include<deque>
#include<unordered_set>
#include<fstream>
#include<map>
using namespace std;

void print_dict(const map<char, vector<string>> &dict)
{
	for (pair<char, vector<string>> temp : dict)
	{
		cout << temp.first << ":";
		for (string s : temp.second)
		{
			cout << s << " ";
		}
		cout << endl;
	}
}

void print_v_s(vector<string> & temp)
{
	for (string s : temp)
		cout << s << ' ';
	cout << endl;
}

bool compare(const string &s1, const string &s2)
{
	return s1.size() > s2.size();
}


void trace_back(const map<char, vector<string>> &dict,string & str, vector<string> &temp, vector<string> &result, int index)
{
	//print_v_s(temp);
	if (index >= str.size())
	{
		if (result.empty())
		{
			result = temp;
			return;
		}
		if (temp.size() < result.size())
			result = temp;
		return;
	}
	//cout << index << endl;
	if (!dict.count(str[index])) return;
	vector<string> dict_temp = dict.at(str[index]);
	for (int i = 0; i < dict_temp.size(); i++)
	{
		int j = 0;
		for (; j < dict_temp[i].size()&&index+j<str.size(); j++)
		{
			if (str[index + j] != dict_temp[i][j])
				break;
		}
		
		if (j == dict_temp[i].size())
		{
			temp.push_back(dict_temp[i]);
			trace_back(dict, str, temp, result, index + dict_temp[i].size());
			temp.pop_back();
		}
	}
}



int main(int argc, const char * argv[])
{
	freopen("input.txt", "r", stdin);
	string strS;
	string dictStr;
	int nDict;
	map<char,vector<string>> dict;
	vector<string> result;
	vector<string> temp;

	while (cin >> strS)
	{
		cin >> nDict;
		for (int i = 0; i < nDict; i++)
		{
			cin >> dictStr;
			if (!dict.count(dictStr[0]))
			{
				dict[dictStr[0]] = temp;
			}
			dict[dictStr[0]].push_back(dictStr);
		}
		for (map<char, vector<string>>::iterator it = dict.begin(); it != dict.end(); it++)
		{
			sort((*it).second.begin(), (*it).second.end(), compare);
		}
		//print_dict(dict);
		vector<string> v_temp;
		trace_back(dict, strS, v_temp, result, 0);
		if (result.empty())
		{
			cout << "n/a" << endl;
		}
		else
		{
			cout << result[0];
			for (int i = 1; i < result.size(); i++)
				cout << ' ' << result[i];
			cout << endl;
		}
	}

	return 0;
}