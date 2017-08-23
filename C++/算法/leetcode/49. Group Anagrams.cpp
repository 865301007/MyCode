//https://leetcode.com/problems/group-anagrams/discuss/
class Solution {
public:
    vector<vector<string>> groupAnagrams(vector<string>& strs) {
        vector<vector<string>> result;
        map<string,int> str_to_index;
        for(string s:strs)
        {
            string temp_s=s;
            sort(s.begin(),s.end());
            if(str_to_index.count(s))
            {
                result[str_to_index.at(s)].push_back(temp_s);
            }
            else
            {
                vector<string> temp;
                temp.push_back(temp_s);
                str_to_index[s]=result.size();
                result.push_back(temp);
            }
        }
        return result;
        
    }
};