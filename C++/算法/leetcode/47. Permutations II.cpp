//https://leetcode.com/problems/permutations-ii/description/
class Solution {
public:
    void trace_back(vector<vector<int>> &result,vector<int>  nums,int index) //这里nums要用值传递而不是引用传递
    {
        if(index==nums.size()-1)
        {
            result.push_back(nums);
            return ;
        }
        for(int i=index;i<nums.size();i++)
        {
            if(i!=index&&nums[i]==nums[index])
            {
                continue;
            }
            else
            {
            swap(nums[index],nums[i]);
            trace_back(result,nums,index+1);
            }
        }
        
    }
    vector<vector<int>> permuteUnique(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        vector<vector<int>> result;
        trace_back(result,nums,0);
        return result;
        
    }
};