//https://leetcode.com/problems/maximum-subarray/description/
class Solution {
public:
    int maxSubArray(vector<int>& nums) {
        if(nums.empty()) return 0;
        int result=nums[0];
        int value=0;
        for(int n:nums)
        {
            value+=n;
            result=max(value,result);
            value=max(value,0);
        }
        return result;
    }
};