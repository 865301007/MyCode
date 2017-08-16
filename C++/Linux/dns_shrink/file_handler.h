

#ifndef DNS_SHRINK_FILE_HANDLER_H_
#define DNS_SHRINK_FILE_HANDLER_H_

#include<fstream>
#include<vector>
#include<string>
#include<sstream>
#include<cctype>
#include<algorithm>

#include<boost/unordered_map.hpp>
#include<boost/unordered_set.hpp>

#include"log.h"
#include"view_node.h"
#include"shrinker.h"
class FileHandler
{
public:
	void resolve_cv(const std::string & cv_file,std::vector<ViewNode> &cv_tree,boost::unordered_map<std::string,int> &name_ro_index,ViewNode * &head);
	void resolver_dn(const std::string & dn_file,std::vector<ViewNode> &cv_tree,const boost::unordered_map<std::string, int> &name_ro_index,const boost::unordered_set<ViewNode*> &r2th_node_ptrs);
	void resolver_dn(const std::string & dn_file, std::vector<ViewNode> &cv_tree, const boost::unordered_map<std::string, int> &name_ro_index, ViewNode * head);
	int  get_cv_tree_size(const std::string & cv_file);
	void get_rsecond_ptrs(const std::vector<ViewNode> &cv_tree, boost::unordered_set<ViewNode *> &r2th_node_ptrs);
	
private:
	void init_cv_tree_ptr(const boost::unordered_map<std::string, int> &name_to_index,std::vector<ViewNode> & cv_tree);
	void resolve_dn_line(const std::string &temp_dn, std::string &domain_name, std::string &cv_name, std::string &config);
	void init_cv_tree_data(std::vector<ViewNode> &cv_tree);
	void print_cv_tree(std::ofstream& dn_out,const std::vector<ViewNode> &cv_tree);

};


#endif // !DNS_SHRINK_FILE_HANDLER_H_

