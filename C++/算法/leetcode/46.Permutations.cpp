//https://leetcode.com/problems/permutations/discuss/
class Solution {
public:
    void trace_back(vector<vector<int>> &result,vector<int> & nums,int index)
    {
        if(index>=nums.size())
        {
            result.push_back(nums);
            return ;
        }
        for(int i=index;i<nums.size();i++)
        {
            swap(nums[index],nums[i]);
            trace_back(result,nums,index+1);
            swap(nums[index],nums[i]);
        }
        
    }
    vector<vector<int>> permute(vector<int>& nums) {
        vector<vector<int>> result;
        trace_back(result,nums,0);
        return result;
        
    }
};