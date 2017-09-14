//https://leetcode.com/problems/minimum-path-sum/description/
class Solution {
public:
    int minPathSum(vector<vector<int>>& grid) {
        if (grid.empty()||grid[0].empty())
            return 0;
        int sum=0;
        for(int i=0;i<grid.size();i++)
        {
            sum+=grid[i][0];
            grid[i][0]=sum;
        }
        sum=0;
        for(int i=0;i<grid[0].size();i++)
        {
            sum+=grid[0][i];
            grid[0][i]=sum;
        }
        for(int i=1;i<grid.size();i++)
        {
            for(int j=1;j<grid[i].size();j++)
            {
                grid[i][j]+=min(grid[i-1][j],grid[i][j-1]);
            }
        }
        return grid.back().back();
    }
};