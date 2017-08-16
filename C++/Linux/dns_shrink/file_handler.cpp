#include "file_handler.h"

//主要是初始化指针指向的位置,此方法暂时未用，如果之后要多线程的话可能会用到
void FileHandler::init_cv_tree_ptr(const boost::unordered_map<std::string, int> &name_to_index, std::vector<ViewNode> & new_cv_tree)
{
	int cv_tree_size = new_cv_tree.size();
	ViewNode *temp_view_node_ptr;
	std::string temp_father_name;
	for (int i = 1; i < cv_tree_size; i++)
	{
		temp_view_node_ptr = &new_cv_tree[i];
		temp_father_name = temp_view_node_ptr->father_name;
		if (temp_father_name.empty())
		{
			temp_view_node_ptr->father_ptr = NULL;
			continue;
		}

		temp_view_node_ptr->father_ptr = &new_cv_tree[name_to_index.at(temp_father_name)];
		temp_view_node_ptr->father_ptr->son_ptrs.push_back(temp_view_node_ptr);
	}
}

//将一行中的域名，cv名和配置解析出来
void FileHandler::resolve_dn_line(const std::string & temp_dn, std::string & domain_name, std::string & cv_name, std::string & config)
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

//初始化域名和config值
void FileHandler::init_cv_tree_data(std::vector<ViewNode>& cv_tree)
{
	int cv_tree_size = cv_tree.size();
	for (int i = 0; i < cv_tree_size; i++)
	{
		cv_tree[i].init_node();
	}
}

//用于输出缩减后的dn
void FileHandler::print_cv_tree(std::ofstream &dn_out, const std::vector<ViewNode> &cv_tree)
{
	int cv_tree_size = cv_tree.size();
	for (int i = 0; i < cv_tree_size; i++)
	{
		if (!cv_tree[i].config.empty())
		{
			dn_out << cv_tree[i] << std::endl;
			//std::cout << cv_tree[i] << std::endl;
		}
	}
}





void FileHandler::resolve_cv(const std::string &cv_file_name, std::vector<ViewNode> &cv_tree, boost::unordered_map<std::string, int> &name_to_index, ViewNode * &head)
{

	std::ifstream cv_in(cv_file_name.c_str());
	std::string temp_cv;
	std::string node_name, father_name;
	std::stringstream ss;
	int i = 1;  //从1开始输入  第0个结点留给特殊结点* 和~
	while (getline(cv_in, temp_cv))
	{
		temp_cv = temp_cv.substr(3, temp_cv.size() - 3);
		std::stringstream ss(temp_cv);
		ss >> node_name >> father_name;
		cv_tree[i].node_name = node_name;
		cv_tree[i].father_name = father_name;
		node_name.clear();
		father_name.clear();
		i++;
	}
	cv_in.close();

	std::sort(cv_tree.begin() + 1, cv_tree.end());//排序，方便之后的顺序输出
	for (int j = 1; j < i; j++)
	{
		if (cv_tree[j].father_name.empty()) head = &(cv_tree[j]);
		name_to_index[cv_tree[j].node_name] = j;
	}
	this->init_cv_tree_ptr(name_to_index, cv_tree);
}

//此方法未被使用
void FileHandler::resolver_dn(const std::string & dn_file, std::vector<ViewNode> &cv_tree, const boost::unordered_map<std::string, int> &name_to_index, const boost::unordered_set<ViewNode*> &r2th_node_ptrs)
{

	std::ifstream dn_in(dn_file.c_str());
	int index = dn_file.rfind("/");
	std::string outfile = "out_" + dn_file.substr(index + 1, dn_file.size() - 1 - index);
	std::ofstream dn_out((outfile).c_str(), std::ios::trunc);

	std::string cur_domain_name, last_domain_name;
	std::string cv_name;
	std::string dn_config;
	std::string temp_dn;
	ViewNode * temp_view_node_ptr = NULL;
	Shrinker shrinker;

	while (getline(dn_in, temp_dn))
	{

		this->resolve_dn_line(temp_dn, cur_domain_name, cv_name, dn_config);
		if (cur_domain_name.empty() || cv_name.empty() || dn_config.empty())
		{
			LogWarn(temp_dn + "is igenall!");
			continue;
		}
		if (!last_domain_name.empty() && last_domain_name != cur_domain_name)//当前域名与上一个域名不一样且上一个域名不为空
		{
			//LogInfo("shrink " + last_domain_name);
			shrinker.shrink(&(cv_tree[name_to_index.at("29")]));
			//shrinker.shrink(r2th_node_ptrs);
			this->print_cv_tree(dn_out, cv_tree);
			this->init_cv_tree_data(cv_tree);
		}

		if (cv_name == "*" || cv_name == "~")
		{
			temp_view_node_ptr = &cv_tree[0];
		}
		else if (name_to_index.count(cv_name))
		{
			temp_view_node_ptr = &cv_tree[name_to_index.at(cv_name)];
		}
		else
		{
			LogWarn(temp_dn + "is igenall!");
			continue;
		}

		temp_view_node_ptr->node_name = cv_name;
		temp_view_node_ptr->config = dn_config;
		temp_view_node_ptr->domain_name = cur_domain_name;

		last_domain_name = cur_domain_name;
		cur_domain_name.clear();
		dn_config.clear();
		cv_name.clear();

	}
	//LogInfo("shrink " + last_domain_name);
	shrinker.shrink(&(cv_tree[name_to_index.at("29")]));
	//shrinker.shrink(r2th_node_ptrs);
	this->print_cv_tree(dn_out, cv_tree);
	dn_in.close();
	dn_out.close();
}

void FileHandler::resolver_dn(const std::string & dn_file, std::vector<ViewNode>& cv_tree, const boost::unordered_map<std::string, int>& name_to_index, ViewNode * head)
{
	std::ifstream dn_in(dn_file.c_str());
	int index = dn_file.rfind("/");
	std::string outfile = "out_" + dn_file.substr(index + 1, dn_file.size() - 1 - index);
	std::ofstream dn_out((outfile).c_str(), std::ios::trunc);

	std::string cur_domain_name, last_domain_name;
	std::string cv_name;
	std::string dn_config;
	std::string temp_dn;
	ViewNode * temp_view_node_ptr = NULL;
	Shrinker shrinker;

	while (getline(dn_in, temp_dn))
	{

		this->resolve_dn_line(temp_dn, cur_domain_name, cv_name, dn_config);
		if (cur_domain_name.empty() || cv_name.empty() || dn_config.empty())
		{
			LogWarn(temp_dn + "is igenall!");
			continue;
		}
		if (!last_domain_name.empty() && last_domain_name != cur_domain_name)//当前域名与上一个域名不一样且上一个域名不为空
		{

			shrinker.shrink(head);

			this->print_cv_tree(dn_out, cv_tree);
			this->init_cv_tree_data(cv_tree);
		}

		if (cv_name == "*" || cv_name == "~")
		{
			temp_view_node_ptr = &cv_tree[0];
		}
		else if (name_to_index.count(cv_name))
		{
			temp_view_node_ptr = &cv_tree[name_to_index.at(cv_name)];
		}
		else
		{
			LogWarn(temp_dn + "is igenall!");
			continue;
		}

		temp_view_node_ptr->node_name = cv_name;
		temp_view_node_ptr->config = dn_config;
		temp_view_node_ptr->domain_name = cur_domain_name;

		last_domain_name = cur_domain_name;
		cur_domain_name.clear();
		dn_config.clear();
		cv_name.clear();

	}

	shrinker.shrink(head);

	this->print_cv_tree(dn_out, cv_tree);
	dn_in.close();
	dn_out.close();
}

int FileHandler::get_cv_tree_size(const std::string & cv_file)
{
	std::ifstream cv_in(cv_file.c_str());
	std::string temp_cv;
	int count = 0;
	while (getline(cv_in, temp_cv))
	{
		count++;
	}
	cv_in.close();
	return count;
}

//此方法未被使用
void FileHandler::get_rsecond_ptrs(const std::vector<ViewNode>& cv_tree, boost::unordered_set<ViewNode*>& r2th_node_ptrs)
{
	int cv_tree_size = cv_tree.size();
	for (int i = 1; i < cv_tree_size; i++)
	{
		if (cv_tree[i].son_ptrs.empty())
		{
			r2th_node_ptrs.insert(cv_tree[i].father_ptr);
		}
	}
}
