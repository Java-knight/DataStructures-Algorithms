package toplikedquestions;

// 删除莲表的倒数第N个节点
// https://leetcode.cn/problems/remove-nth-node-from-end-of-list/
public class Problem_0019_RemoveNthNodeFromEndOfList {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = null;
        ListNode cur = head;
        boolean flag = false;  // 倒数第N个节点是否存在
        while (cur != null) {
            n--;
            if (n <= 0) {
                if (n == 0) {  // 存在需要删除的倒数第N个节点
                    flag = true;
                } else if (n == -1) {  // 走到了需要删除N+1位置
                    pre = head;
                } else {
                    pre = pre.next;
                }
            }
            cur = cur.next;
        }

        if (!flag) {  // base case1 不存在倒数第N个节点
            return head;
        }
        if (pre == null) {  // base case2 需要删除的倒数第N个节点是头节点
            return head.next;
        }
        // base case3 正常情况
        pre.next = pre.next.next;
        return head;
    }

//    // 可以省略一个变量（面试吹牛逼的，没啥用）
//    public ListNode removeNthFromEnd(ListNode head, int n) {
//        ListNode pre = null;
//        ListNode cur = head;
//        while (cur != null) {
//            n--;
//            if (n == -1) {  // 走到了需要删除N+1位置
//                pre = head;
//            }
//            if (n < -1) {
//                pre = pre.next;
//            }
//            cur = cur.next;
//        }
//
//        if (n > 0) {  // base case1 不存在倒数第N个节点
//            return head;
//        }
//        if (pre == null) {  // base case2 需要删除的倒数第N个节点是头节点
//            return head.next;
//        }
//        // base case3 正常情况
//        pre.next = pre.next.next;
//        return head;
//    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) {this.val = val;}
        ListNode(int val, ListNode next) {this.val = val;this.next = next;}
    }
}
