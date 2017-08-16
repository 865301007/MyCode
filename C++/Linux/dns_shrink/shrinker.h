
#include<vector>

#include<boost/unordered_set.hpp>
#include"view_node.h"
#include"log.h"

class Shrinker
{
public:
	void shrink(boost::unordered_set<ViewNode*> r2th_node_ptrs);
	bool shrink(ViewNode * node);
private:
	bool shrinkNode(ViewNode * node_ptr);
};

