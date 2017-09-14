//https://leetcode.com/problems/add-binary/discuss/
class Solution {
public:
    string add(string &a,string &b)
    {
        if(a.size()==1&&a[0]=='0')
            return b;
        if(b.size()==1&&b[0]=='0')
            return a;
        string result(a.size()+1,0);
        int j=0;
        for(int i=b.size()-1;i>=0;i--)
        {
            j=a.size()-b.size()+i;
            result[j+1]+=a[j]-'0'+b[i]-'0';
            //cout<<(int)result[j+1]<<' '<<a[j]<<' '<<b[i]<<endl;
            result[j]+=result[j+1]/2;
            result[j+1]%=2;
            result[j+1]+='0';
        }
        for(j=a.size()-b.size()-1;j>=0;j--)
        {
              result[j+1]+=a[j]-'0';
              result[j]+=result[j+1]/2;
              result[j+1]%=2;
              result[j+1]+='0';
            
        }
        result[0]+='0';
        return result[0]=='0'?result.substr(1,result.size()-1):result;
    }
    string addBinary(string a, string b) {
        return a.size()>b.size()?add(a,b):add(b,a);
    }
};