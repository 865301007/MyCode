//https://leetcode.com/problems/spiral-matrix-ii/description/
class Solution {
public:
        void recu(vector<vector<int>>& matrix,int l,int t,int r,int b,int index)
    {
        if(l<0||t<0||r<0||b<0||l>r||t>b) return;
        for(int i=l;i<=r;i++)
            matrix[t][i]=index++;
 
        for(int i=t+1;i<=b;i++)
            matrix[i][r]=index++;
        
        if(t==b) return;
  
        for(int i=r-1;i>=l;i--)
            matrix[b][i]=index++;
        
        if(l==r) return;
      
        for(int i=b-1;i>=t+1;i--)
            matrix[i][l]=index++;
      
        recu(matrix,l+1,t+1,r-1,b-1,index);
        
    }
    vector<vector<int>> generateMatrix(int n) {
        vector<int> temp(n,0);
        vector<vector<int>> result(n,temp);
        recu(result,0,0,n-1,n-1,1);
        return result;
    }
};