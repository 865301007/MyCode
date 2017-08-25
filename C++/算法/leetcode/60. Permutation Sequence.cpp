//https://leetcode.com/problems/permutation-sequence/discuss/
class Solution {
public:
    string getPermutation(int n, int k) {
        string result;
        string data(n,0);
        int xxxx=1;
        for(int i=1;i<=n;i++)
        {
            xxxx*=i;
            data[i-1]=i+'0';
        }
      
        int index;
        while(data.size())
        {
            xxxx/=n;
            index=(k-1)/xxxx;
            result.push_back(data[index]);
            data.erase(data.begin()+index);
            k-=index*xxxx;
            n=n-1;
            
        }
     
        return result;
        
    }
};