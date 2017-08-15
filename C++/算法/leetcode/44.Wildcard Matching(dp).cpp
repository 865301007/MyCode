//https://leetcode.com/problems/wildcard-matching/discuss/
class Solution {
public:
    bool isMatch(string s, string p) {
        if(s.empty())
        {
            for(char c:p)
                if(c!='*') return false;
            return true;
        }
        if(p.empty()) return false;
        vector<bool> d(p.size(),false);
        vector<vector<bool>> dp(s.size(),d);
        dp[0][0]=s[0]==p[0]||p[0]=='?'||p[0]=='*';
        for(int j=1;j<p.size();j++)
        {
            if(p[j]=='*')
            {
                dp[0][j]=dp[0][j-1];
                continue;
            }
            if(p[j]=='?'||p[j]==s[0])
            {
                dp[0][j]=true;
                for(int k=0;k<j;k++)
                {
                    if(p[k]!='*') dp[0][j]=false;
                }
                continue;
            }
            dp[0][j]=false;
            
        }
        for(int i=1;i<s.size();i++)
        {
            dp[i][0]=p[0]=='*';
        }
        for(int i=1;i<s.size();i++)
        {
            for(int j=1;j<p.size();j++)
            {
                if(p[j]=='*')
                {
                    for(int k=0;k<=i;k++)
                        dp[i][j]=dp[i][j]||dp[k][j-1];
                    continue;
                }
                if(p[j]=='?'||s[i]==p[j])
                {
                    dp[i][j]=dp[i-1][j-1];
                    continue;
                }
                dp[i][j]=false;
            }
        }
            
        return dp[s.size()-1][p.size()-1];
    }
};