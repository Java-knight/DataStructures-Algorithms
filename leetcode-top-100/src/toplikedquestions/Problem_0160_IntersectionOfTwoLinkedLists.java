package toplikedquestions;

// 相交链表
// https://leetcode.cn/problems/intersection-of-two-linked-lists/
public class Problem_0160_IntersectionOfTwoLinkedLists {

    // 思路1: 先用两个指针指向A\B链表的最后一个元素. 如果最后一个元素不相等, 表示没有相交节点, 直接返回null;
    // 反之, 则找出相交节点(长链表先走多余的路, 然后在一起走)
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode cur1 = headA;
        ListNode cur2 = headB;
        int count = 0;
        while (cur1.next != null) {
            count++;
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            count--;
            cur2 = cur2.next;
        }
        // (1) 判断是否存在相交的节点
        if (cur1 != cur2) {
            return null;
        }
        // (2) 存在相交节点找出来相交节点
        cur1 = count > 0 ? headA : headB;      // cur1指向长链表
        cur2 = cur1 == headA ? headB : headA;  // cur2指向短链表
        count = Math.abs(count);  // 取正数, 长链表比短链表长的值
        while(count != 0) {  // 长链表先走count步
            count--;
            cur1 = cur1.next;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    // 思路2: 定义两个指针, cur1和cur2, cur1先在A链表上跑
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        ListNode cur1 = headA;
        ListNode cur2 = headB;
        while (cur1 != cur2) {
            cur1 = cur1 == null ? headB : cur1.next;
            cur2 = cur2 == null ? headA : cur2.next;
        }
        return cur1;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
