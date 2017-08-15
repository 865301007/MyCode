//https://leetcode.com/problems/combination-sum-ii/description/
class Solution {
public:
    void trace_back(vector<int> & candidates,int target,int sum,int start,vector<int> &temp,vector<vector<int>> &result)
    {
        if(sum==target)
        {
            result.push_back(temp);
            return ;
        }
        if(sum>target||start>=candidates.size()) return;

        for(int i=start;i<candidates.size();i++)
        {
            if(i&&candidates[i]==candidates[i-1]&&i>start) continue;//这一步很关键
            sum+=candidates[i];
            temp.push_back(candidates[i]);
            trace_back(candidates,target,sum,i+1,temp,result);
            temp.pop_back();
            sum-=candidates[i];
        }
    }
    vector<vector<int>> combinationSum2(vector<int>& candidates, int target) {
        
        sort(candidates.begin(),candidates.end());
        vector<int>temp;
        vector<vector<int>> result;
        int sum=0;
        int start=0;
        trace_back(candidates,target,sum,start,temp,result);
        return result;
    }
};
