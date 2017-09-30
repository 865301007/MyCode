
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
#include<set>
using namespace std;

bool is_ok( const string  &temp)
{
	string new_temp=temp;
	sort(new_temp.begin(), new_temp.end());

	int n = atoi(temp.c_str());
	while (next_permutation(new_temp.begin(),new_temp.end()))
	{
		//cout << temp << endl;
		if (new_temp!=temp&& atoi(new_temp.c_str())%n == 0)
		{
			//cout << temp << ' ' << new_temp << endl;
			return true;
		}
	}
	return false;
}


int main() {
	//freopen("input.txt", "r", stdin);
	int n;
	string temp;
	while (cin>>n)
	{
		for (int i = 0; i < n; i++)
		{
			cin >> temp;
			if (is_ok(temp))
			{
				cout << "Possible" << endl;
			}
			else
			{
				cout << "Impossible" << endl;
			}
		}
	}
	return 0;

}