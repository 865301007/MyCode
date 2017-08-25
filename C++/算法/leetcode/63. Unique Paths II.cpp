//https://leetcode.com/problems/unique-paths-ii/description/
class Solution {
public:
    void print(vector<vector<int>>& obstacleGrid)
    {
         for(int i=0;i<obstacleGrid.size();i++)
         {
            for(int j=0;j<obstacleGrid[i].size();j++)
            {
              cout<<obstacleGrid[i][j]<<' ';
            }
             cout<<endl;
         }
    }
    int uniquePathsWithObstacles(vector<vector<int>>& obstacleGrid) {
        if(obstacleGrid.empty()) return 0;
        for(int i=0;i<obstacleGrid[0].size()&&obstacleGrid[0][i]!=1;i++)
            obstacleGrid[0][i]=-1;
        for(int i=0;i<obstacleGrid.size()&&obstacleGrid[i][0]!=1;i++)
            obstacleGrid[i][0]=-1;
        for(int i=1;i<obstacleGrid.size();i++)
        {
            for(int j=1;j<obstacleGrid[i].size();j++)
            {
                if(obstacleGrid[i][j]>0) continue;
                if(obstacleGrid[i-1][j]<0)
                {
                    obstacleGrid[i][j]+=obstacleGrid[i-1][j];
                }
                if(obstacleGrid[i][j-1]<0)
                {
                    obstacleGrid[i][j]+=obstacleGrid[i][j-1];
                }
            }
        }
     
       // print(obstacleGrid);
       
        return obstacleGrid.back().back()>0?0:-obstacleGrid.back().back();
    }
};