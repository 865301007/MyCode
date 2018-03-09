#include<iostream>
#include<iomanip>
using namespace std;
int A[16] = { 0 };

void init()
{
	for (int i = 1; i <= 13; i++)
	{
		A[i] = 4;
	}

	A[14] = 1;
	A[15] = 1;

}

void print()
{
	cout << "***************************************************" << endl;
	for (size_t i = 1; i <= 15; i++)
	{
		cout << setw(4) << i <<setw(4)<< A[i] << endl;
	}

}
int main()
{

	int n = 0;
	init();
	while (cin>>n)
	{

		if (n == 0)
		{
			init();
			continue;
		}

		A[n]--;
		print();
	}
}