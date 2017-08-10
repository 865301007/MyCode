//https://leetcode.com/problems/combination-sum/description/
class Solution {
public:
    void trace_back(vector<int> & candidates,int target,int sum,int start,vector<int> &temp,vector<vector<int>> &result)
    {
        if(sum>target||start>=candidates.size()) return;
        if(sum==target)
        {
            result.push_back(temp);
            return ;
        }
        
        for(int i=start;i<candidates.size();i++)
        {
            sum+=candidates[i];
            temp.push_back(candidates[i]);
            trace_back(candidates,target,sum,i,temp,result);
            temp.pop_back();
            sum-=candidates[i];
        }
    }
    vector<vector<int>> combinationSum(vector<int>& candidates, int target) {
        
        vector<int>temp;
        vector<vector<int>> result;
        int sum=0;
        int start=0;
        trace_back(candidates,target,sum,start,temp,result);
        return result;
    }
};