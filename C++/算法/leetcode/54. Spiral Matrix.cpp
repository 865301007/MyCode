//https://leetcode.com/problems/spiral-matrix/description/
class Solution {
public:
    void recu(vector<vector<int>>& matrix,int l,int t,int r,int b,vector<int> &result)
    {
        if(l<0||t<0||r<0||b<0||l>r||t>b) return;
        for(int i=l;i<=r;i++)
            result.push_back(matrix[t][i]);
 
        for(int i=t+1;i<=b;i++)
            result.push_back(matrix[i][r]);
        
        if(t==b) return;
  
        for(int i=r-1;i>=l;i--)
            result.push_back(matrix[b][i]);
        
        if(l==r) return;
      
        for(int i=b-1;i>=t+1;i--)
            result.push_back(matrix[i][l]);
      
        recu(matrix,l+1,t+1,r-1,b-1,result);
        
    }
    vector<int> spiralOrder(vector<vector<int>>& matrix) {
        
        vector<int> result;
        if(matrix.empty()) return result;
        recu(matrix,0,0,matrix[0].size()-1,matrix.size()-1,result);
        return result;
        
    }
};