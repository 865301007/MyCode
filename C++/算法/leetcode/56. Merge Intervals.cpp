//https://leetcode.com/problems/merge-intervals/description/
/**
 * Definition for an interval.
 * struct Interval {
 *     int start;
 *     int end;
 *     Interval() : start(0), end(0) {}
 *     Interval(int s, int e) : start(s), end(e) {}
 * };
 */


class Solution {
public:
    vector<Interval> merge(vector<Interval>& intervals) {
        if(intervals.empty()) return intervals;
        sort(intervals.begin(),intervals.end(),[](Interval & a,Interval &b){return a.start<b.start;});
        vector<Interval> result;
        result.push_back(intervals[0]);
        for(int i=1;i<intervals.size();i++)
        {
            if(intervals[i].start<=result.back().end)
                result.back().end=max(result.back().end,intervals[i].end);
            else
                result.push_back(intervals[i]);
        }

        return result;
    }
};