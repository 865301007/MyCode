//https://leetcode.com/problems/count-and-say/description/
class Solution {
public:
    string countAndSay(int n) {
        string s;
        if(n<=0) return s;
        s="1";
        string temp;
        for(int i=2;i<=n;i++)
        {
            int count=1;
            char cur=s[0];
            for(int i=1;i<s.size();i++)
            {
                if(s[i]==cur)
                {
                    count++;
                }
                else
                {
                    temp+=to_string(count);
                    temp.push_back(cur);
                    count=1;
                    cur=s[i];
                }
            }
            temp+=to_string(count);
            temp.push_back(cur);
            s=temp;
            temp.clear();
        }
        return s;
    }
};