
/*
写dns_shrink时用来画图的辅助程序
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

struct node
{

	int index;
	int config;
	node()
	{
		config = -1;
	}
	friend	ostream & operator <<(ostream &out, const node &n);
};
ostream & operator <<(ostream &out, const node &n)
{
	out << "\"n" << n.index << " " << "c" << n.config << "\"";
	return out;
}

void resolve_dn_line(const std::string & temp_dn, std::string & domain_name, std::string & cv_name, std::string & config)
{
	int start = 0, end = 0;
	while (isspace(temp_dn[start])) ++start;
	end = start;
	while (!isspace(temp_dn[end])) ++end;
	domain_name = temp_dn.substr(start, end - start);


	start = end;
	while (isspace(temp_dn[start])) ++start;
	end = start;
	while (!isspace(temp_dn[end])) ++end;
	cv_name = temp_dn.substr(start, end - start);

	start = end;
	while (isspace(temp_dn[start])) ++start;
	end = temp_dn.size() - 1;
	while (isspace(temp_dn[end])) --end;
	end = end + 1;
	config = temp_dn.substr(start, end - start);


}
int main()
{
	freopen("output.txt", "w", stdout);
	vector<node> nodes(761, node());
	for (int i = 0; i < 761; i++)
		nodes[i].index = i;

	map<string, int> m;
	int config_index = 0;
	ifstream in("test_ourdvs.conf");
	string temp_dn, node_name, domain_name, config;
	while (getline(in,temp_dn))
	{
		resolve_dn_line(temp_dn, domain_name, node_name, config);
		if (!m.count(config))
		{
			m[config] = config_index++;
		}
		nodes[atoi(node_name.c_str())].config = m[config];
	}
	in.close();

	in.open("view.conf");
	int n1, n2;
	cout << "g" << endl << "{" << endl;
	while (getline(in,temp_dn))
	{
		stringstream ss(temp_dn.substr(3, temp_dn.size() - 3));
		ss >> n1 >> n2;
		cout << nodes[n2] << "->" << nodes[n1] << ";"<< endl;
	}
	cout << "}" << endl;


}