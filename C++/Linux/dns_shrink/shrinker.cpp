#include "shrinker.h"

//此方法未被使用
void Shrinker::shrink(boost::unordered_set<ViewNode*> r2th_node_ptrs)
{
	boost::unordered_set<ViewNode*> temp_ptrs;
	while (r2th_node_ptrs.size())
	{
		for (boost::unordered_set<ViewNode *>::iterator it = r2th_node_ptrs.begin(); it != r2th_node_ptrs.end();it++)
		{
			if (shrinkNode(*it)&&(*it)->father_ptr)
			{
				temp_ptrs.insert((*it)->father_ptr);
			}
		}
		r2th_node_ptrs.clear();
		r2th_node_ptrs = temp_ptrs;
		temp_ptrs.clear();
	}
}


//递归的方法
bool Shrinker::shrink(ViewNode * node)
{
	if (node->son_ptrs.size() == 0)
	{
		return true;
	}
	std::string config = node->config.empty() ? node->son_ptrs[0]->config : node->config;
	std::string domain = node->config.empty() ? node->son_ptrs[0]->domain_name : node->domain_name;
	
	bool can_shrink = true;
	for (int i = 0; i < node->son_ptrs.size(); i++)
	{
		if (!(shrink(node->son_ptrs[i])&&config == node->son_ptrs[i]->config))
		{
			can_shrink = false;
		}
	}
	if (!can_shrink) return false;
	node->domain_name = domain;
	node->config = config;
	for (int i = 0; i < node->son_ptrs.size(); i++)
	{
		node->son_ptrs[i]->config.clear();
		node->son_ptrs[i]->domain_name.clear();
	}
	return true;
}

bool Shrinker::shrinkNode(ViewNode * node_ptr)
{
	std::string temp_config = node_ptr->config.empty() ? node_ptr->son_ptrs[0]->config : node_ptr->config;
	std::string temp_domain_name = node_ptr->config.empty() ? node_ptr->son_ptrs[0]->domain_name : node_ptr->domain_name;
	for (int i = 0; i < node_ptr->son_ptrs.size(); i++)
	{
		if (temp_config != node_ptr->son_ptrs[i]->config)
		{
			return false;
		}
	}
	node_ptr->config = temp_config;
	node_ptr->domain_name = temp_domain_name;
	for (int i = 0; i < node_ptr->son_ptrs.size(); i++)
	{
		node_ptr->son_ptrs[i]->config.clear();
		node_ptr->son_ptrs[i]->domain_name.clear();
	}
}

