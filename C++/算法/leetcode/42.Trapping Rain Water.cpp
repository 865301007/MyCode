class Solution {
public:
    int sum(vector<int> & height,int start,int end)
    {
        int result=0;
        int max=min(height[start],height[end]);
       // cout<<max<<endl;
        for(int i=start+1;i<end;i++)
        {
            result+=max-height[i];
        }
        
        return result;
    }
    int trap(vector<int>& height) {
        int i=0;
        int j=0;
        int result=0;
        while(i<height.size())
        {
            int next_height=0;
            int next_index=i+1;
            for(j=height.size()-1;j>=i+1;j--)
            {
                if(height[j]>next_height||height[j]>=height[i])
                {
                    next_height=height[j];
                    next_index=j;
                }
            }
            result+=sum(height,i,next_index);
            i=next_index;
        }
        return result;
        
    }
};