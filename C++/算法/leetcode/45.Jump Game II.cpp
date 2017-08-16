//https://leetcode.com/problems/jump-game-ii/description/
class Solution {
public:
	int jump(vector<int>& nums) {
		int  index = 0;
		int  result = 0;
		int max_next_index = 0;
		int next_index;
		// if(nums.size()<2) return 0;
		//if(nums[0]>=nums.size()-1) return 1;

		while (index != nums.size() - 1)
		{
			if (index + nums[index] >= nums.size() - 1)
			{
				result++;
				break;
			}
			for (int i = index + 1; i <= index + nums[index]; i++)
			{
				if (max_next_index < i + nums[i])
				{
					max_next_index = i + nums[i];
					next_index = i;
				}

			}
			result++;
			if (max_next_index >= nums.size() - 1)
			{
				result++;
				break;
			}
			index = next_index;


		}
		return result;
	}
};