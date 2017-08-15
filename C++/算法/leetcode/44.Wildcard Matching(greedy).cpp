//https://leetcode.com/problems/wildcard-matching/discuss/
class Solution {
public:
    bool isMatch(string s, string p) {
      int m=s.size(),n=p.size();
        int i=0,j=0,last_i=0,last_j=-1;
        while(i<m)
        {
            if(j<n&&s[i]==p[j]||p[j]=='?')
            {
                i++;
                j++;
            }
            else if(j<n&&p[j]=='*')
            {
                last_i=i;
                last_j=j++;
            }
            else if(last_j!=-1)
            {
                j=last_j;
                i=last_i+1;
            }
            else
            {
                return false;
            }
        }
        for(;j<n;j++)
            if(p[j]!='*') break;
        return j==n;
                
    }
};