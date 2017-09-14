//#include "header.h"	//AnycodeX includes the header.h by default, needn't cancle the notation.
#include <iostream>
#include <algorithm>
#include <string>
#include<cmath>
#include<vector>
#include<map>
#include<cctype>
using namespace std;

class point
{
public:
	point(int r, int c) :raw(r), col(c) {	}
	int raw, col;
	//friend ostream & opretor << (ostream & out, const point &p);

};
ostream & operator << (ostream & out, const point &p)
{
	out << '(' << p.raw << ',' << p.col << ')';
	return out;
};

vector<point> result;

vector<vector<int>>m;
void print()
{
	for (point p : result)
		cout << p << endl;
}
bool isbound(int r, int c, int maxr, int maxc)
{
	return r >= 0 && r <= maxr&&c >= 0 && c <= maxc;
}
void traceback(int r, int c, int maxr, int maxc)
{
	m[r][c] = 2;
	result.push_back(point(r, c));
	if (r == maxr&&c == maxc)
	{
		print();
		return;
	}
	point p = result.size() ? result.back() : point(-1, -1);
	if (isbound(r - 1, c, maxr, maxc) && (r - 1 != p.raw || c != p.col) && !m[r - 1][c])
	{
		traceback(r - 1, c, maxr, maxc);
		result.pop_back();
	}
	if (isbound(r + 1, c, maxr, maxc) && (r + 1 != p.raw || c != p.col) && !m[r + 1][c])
	{
		traceback(r + 1, c, maxr, maxc);
		result.pop_back();
	}
	if (isbound(r, c - 1, maxr, maxc) && (r != p.raw || c - 1 != p.col) && !m[r][c - 1])
	{
		traceback(r, c - 1, maxr, maxc);
		result.pop_back();
	}
	if (isbound(r, c + 1, maxr, maxc) && (r != p.raw || c + 1 != p.col) && !m[r][c + 1])
	{
		traceback(r, c + 1, maxr, maxc);
		result.pop_back();
	}
}
int main()
{
	freopen("input.txt", "r", stdin);
	int r, c;
	while (cin >> r >> c)
	{
		result.clear();
		m.clear();
		for (size_t i = 0; i < r; i++)
		{
			vector<int> v(c);
			for (size_t i = 0; i < c; i++)
			{
				cin >> v[i];
			}
			m.push_back(v);
		}
		traceback(0, 0, r - 1, c - 1);
	}
}