class Solution {
public:
    int firstMissingPositive(vector<int>& nums) {
        if(nums.empty()) return 1;
        int result;
        for(int i=0;i<nums.size();i++)
        {
            if(nums[i]>0&&nums[i]<=nums.size()&&nums[nums[i]-1]!=nums[i])
            {
                swap(nums[i],nums[nums[i]-1]);
                i--;
                /*
                for(auto n:nums)
                    cout<<n<<' ';
                cout<<endl;
                */
            }
        }
        for(int i=0;i<nums.size();i++)
        {
            //cout<<nums[i]<<' ';
            if(nums[i]!=i+1)
                return i+1;
        }
        return nums.size()+1;
    
  
    }
};