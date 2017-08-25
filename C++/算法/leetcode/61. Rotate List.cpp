//https://leetcode.com/problems/rotate-list/description/
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    ListNode* rotateRight(ListNode* head, int k) {
        if(!head||!head->next||k<=0) return head;
        
        ListNode *q=head;
        ListNode *p=head;
        int list_length=k;
        while(k--)
        {
            q=q->next;
            if(!q)
            {
                list_length-=k;
                k%=list_length;
                q=head;
            }
        }
       
        while(q->next)
        {
            p=p->next;
            q=q->next;
        }
        q->next=head;
        head=p->next;
        p->next=NULL;
        return head;
        
    }
};