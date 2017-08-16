//将从wireshack拷贝下来的数据转换为16进制数据
#include <iostream>
using namespace std;
int main()
{
	string s;
	int n=0;
	while(cin>>s)
	{
	    if(s.length()>2) continue;
	    cout<<"0x"<<s<<",  ";
	    
	    n++;
	    if(n%8==0)
	    cout<<endl;
	}
}