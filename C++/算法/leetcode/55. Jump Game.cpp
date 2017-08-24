//https://leetcode.com/problems/jump-game/description/
class Solution {
public:
    bool canJump(vector<int>& nums) {
        int cur_index=0;
        int max_next=0;
        int temp_index=0;
        while(cur_index+nums[cur_index]<nums.size()-1)
        {
            max_next=cur_index;
            for(int i=cur_index+1;i<=cur_index+nums[cur_index];i++)
            {
                if(i+nums[i]>max_next)
                {
                    max_next=i+nums[i];
                    temp_index=i;
                }
            }
            
            if(cur_index==max_next)
            {
                return false;
            }
            cur_index=temp_index;
        }
        return true;
        
    }
};