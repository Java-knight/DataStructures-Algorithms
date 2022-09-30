package toplikedquestions;



// 删除排序链表中的重复元素
// https://leetcode.cn/problems/remove-duplicates-from-sorted-list/
public class Problem_0083_RemoveDuplicatesFromSortedList {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == head) {
            return head;
        }
        ListNode pre = head;
        ListNode next = head.next;
        while (next != null) {  // 出现重复
            if (pre.val == next.val) {
                pre.next = next.next;
            } else {  // 没有重复
                pre = next;
            }
            next = pre.next;
        }
        return head;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) {this.val = val;}
        ListNode(int val, ListNode next) {this.val = val;this.next = next;}
    }
}
