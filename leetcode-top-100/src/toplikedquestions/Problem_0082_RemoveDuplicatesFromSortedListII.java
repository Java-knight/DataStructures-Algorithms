package toplikedquestions;

// 删除链表中的重复元素II
// https://leetcode.cn/problems/remove-duplicates-from-sorted-list-ii/
public class Problem_0082_RemoveDuplicatesFromSortedListII {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode result = new ListNode();  // 虚拟头节点
        result.next = head;
        ListNode cur = result;
        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {  // 出现重复
                int val = cur.next.val;
                while (cur.next != null && cur.next.val == val) {
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }
        return result.next;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) {this.val = val;}
        ListNode(int val, ListNode next) {this.val = val;this.next = next;}
    }
}
