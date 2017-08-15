//https://leetcode.com/problems/multiply-strings/description/
class Solution {
public:
    void print(vector<int> &s)
    {
        for(int c:s)
            cout<<(int)c<<' ';
        cout<<endl;
    }
    string multiply(string num1, string num2) {
        vector<int> result(num1.size()+num2.size(),0);
      //  print(result);
        reverse(num1.begin(),num1.end());
        reverse(num2.begin(),num2.end());
        for(int i=0;i<num1.size();i++)
        {
            for(int j=0;j<num2.size();j++)
            {
                result[i+j]+=(int)(num1[i]-'0')*(int)(num2[j]-'0');
            }
        }
        for(int i=0;i<result.size()-1;i++)
        {
            result[i+1]+=result[i]/10;
            result[i]=result[i]%10;
        }
        int i=result.size()-1;
        while(!result[i]) i--;
        string s;
        for(;i>=0;i--)
            s.push_back(result[i]+'0');
        return s.empty()?"0":s;
        
    }
};