//https://leetcode.com/problems/text-justification/description/
class Solution {
public:
    vector<string> fullJustify(vector<string>& words, int maxWidth) {
        if(words.empty())
            return words;
        int i=0,j=0,k=0;
        vector<string> result;
        int n=words.size();
        int sum=0;
        int word_count=0,space_count=0;
        int jiange=0,left_more=0;
        string temp;
        while(i<n)
        {
            sum=0;
            for(j=i;j<n;j++)
            {
                sum+=words[j].size();
                if(sum+j-i>maxWidth)
                {
                    sum=sum-words[j].size();
                    break;
                }
            }
            
            word_count=j-i;
            if(word_count==1)
            {
               // cout<<words[i]<<endl;
                result.push_back(words[i]+string(maxWidth-words[i].size(),' '));
                i=j;
                continue;
            }
        
            
            if(j==n)
            {
               for(k=i;k<n-1;k++)
               {
                   temp+=words[k]+' ';
               }
              //  cout<<words[n-1]<<endl;
                temp+=words[n-1];
                result.push_back(temp+string(maxWidth-temp.size(),' '));
                break;
            }
            
            
            space_count=maxWidth-sum;
            jiange=space_count/(word_count-1);
            left_more=space_count%(word_count-1);
            for(k=i;k<i+left_more;k++)
            {
                temp+=words[k]+string(jiange+1,' ');
            }
            for(;k<j-1;k++)
            {
                temp+=words[k]+string(jiange,' ');
            }
            temp+=words[j-1];
            
            result.push_back(temp);
            temp.clear();
            i=j;
        }
        return result;
        
    }
};