package toplikedquestions;


// 分隔链表
// https://leetcode.cn/problems/partition-list/
public class Problem_0086_PartitionList {

    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }

        // 搞两个新链表(尾插法)
        ListNode minHead = new ListNode();
        ListNode maxHead = new ListNode();
        ListNode min = minHead;
        ListNode max = maxHead;
        while (head != null) {
            ListNode next = head.next;
            head.next = null;
            if (head.val < x) {
                min.next = head;
                min = min.next;
            } else {
                max.next = head;
                max = max.next;
            }
            head = next;
        }
        System.gc();
        min.next = maxHead.next;
        return minHead.next;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) {this.val = val;}
        ListNode(int val, ListNode next) {this.val = val;this.next = next;}
    }

}
