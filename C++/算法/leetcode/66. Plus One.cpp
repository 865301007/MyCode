//https://leetcode.com/submissions/detail/118339623/
class Solution {
public:
    vector<int> plusOne(vector<int>& digits) {
        if(digits.empty())
            return digits;
        vector<int> result;
        digits[digits.size()-1]+=1;
        for(int i=digits.size()-1;i>=1;i--)
        {
            if(digits[i]>=10)
            {
                digits[i-1]+=1;
                digits[i]-=10;
            }
        }
        if(digits[0]>=10)
        {
            digits[0]-=10;
            result.push_back(1);
        }
        for(int n:digits)
            result.push_back(n);
        return result;
    }
};