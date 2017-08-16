
#ifndef DNS_SHRINK_VIEW_NODE_H
#define DNS_SHRINK_VIEW_NODE_H


#include<vector>
#include<string>
#include<iostream>
struct ViewNode
{
	ViewNode *father_ptr;
	std::vector<ViewNode *> son_ptrs;
	std::string node_name;
	std::string father_name;
	std::string config;
	std::string domain_name;

	ViewNode()
	{
		father_ptr = NULL;
	}

	void init_node()
	{
		config.clear();
		domain_name.clear();
	}

	bool operator <(const ViewNode &v)const //此处一定要用const修饰
	{
		if(node_name.size()==v.node_name.size())
		return this->node_name < v.node_name;
		return node_name.size() < v.node_name.size();
	}
	inline	friend std::ostream &operator <<(std::ostream &out, const ViewNode &v);
};
//在头文件中定义的函数一定要用inline修饰，否则会出现多定义的错误
inline std::ostream &operator <<(std::ostream &out, const ViewNode &v)
{
	out << v.domain_name << ' ' << v.node_name << ' ' << v.config;
	return out;
}
#endif // !DNS_SHRINK_VIEW_NODE_H
