//https://leetcode.com/problems/search-a-2d-matrix/description/
class Solution {
public:
    int searchRow(vector<vector<int>> &matrix,int t,int b,int target)
    {
        if(target>=matrix[b][0]) return b;
        int m=(t+b)/2;
       // cout<<m<<endl;
        return target<matrix[m][0]?searchRow(matrix,t,m-1,target):searchRow(matrix,m,b-1,target);
    }
    
    bool search(vector<int> &v,int l,int r,int target)
    {
        if(r<l) return false;
        int m=(l+r)/2;
        if(v[m]==target) return true;
        return v[m]>target?search(v,l,m-1,target):search(v,m+1,r,target);
    }
    bool searchMatrix(vector<vector<int>>& matrix, int target) {
        if(matrix.empty()||matrix[0].empty()||matrix[0][0]>target) return false;
        //cout<<searchRow(matrix,0,matrix.size()-1,target)<<endl;
       // return true;
        return search(matrix[searchRow(matrix,0,matrix.size()-1,target)],0,matrix.back().size()-1,target);
        
    }
};