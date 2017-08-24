//https://leetcode.com/problems/length-of-last-word/description/
class Solution {
public:
    int lengthOfLastWord(string s) {
        int index=s.size()-1;
        int result=0;
        while(index>=0&&isspace(s[index])) index--;
        while(index>=0&&!isspace(s[index]))
        {
            result++;
            index--;
        }
        return result;
              
    }
};