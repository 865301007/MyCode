//https://leetcode.com/problems/n-queens-ii/description/
class Solution {
public:
    bool can_place_here(vector<string> &v,int r,int c)
    {
        int sum = r + c;
		int ab = r - c;
        for(int i=0;i<v.size();i++)
        {
            if(v[r][i]!='.') return false;
            if(v[i][c]!='.') return false;
            if (0 <= sum - i&&sum - i < v.size() && v[i][sum - i] != '.')return false;
			if (0 <= i - ab&&i - ab < v.size() && v[i][i - ab] != '.') return false;
        }
        if(r==c)
        {
            for(int i=0;i<v.size();i++)
                if(v[i][i]!='.') return false;
        }
        return true;
    }
    void trace_back(vector<string> &v,int &result,int n)
    {
        if(n<0)
        {
            result++;
            return;
        }
        
        for(int i=0;i<v.size();i++)
        {
            if(can_place_here(v,n,i))
            {
                v[n][i]='Q';
                trace_back(v,result,n-1);
                v[n][i]='.';
            }
        }
    }
   int totalNQueens(int n) {
       string line(n,'.');
        vector<string> v(n,line);
        int result=0;
        trace_back(v,result,n-1);
        return result;
    
}
};