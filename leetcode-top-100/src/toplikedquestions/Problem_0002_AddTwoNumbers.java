package toplikedquestions;

// 两数相加
// https://leetcode.cn/problems/add-two-numbers/?favorite=2cktkvj
public class Problem_0002_AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;  // 进位
        int num1 = 0;  // 表示遍历 list1 上的每个 val
        int num2 = 0;  // 表示遍历 list2 上的每个 val
        int sum = 0;  // sum = num1 + num2
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        ListNode node = null;
        ListNode pre = null;
        while (cur1 != null || cur2 != null) {
            num1 = cur1 != null ? cur1.val : 0;
            num2 = cur2 != null ? cur2.val : 0;
            sum = num1 + num2 + carry;
            pre = node;
            node = new ListNode(sum % 10);
            node.next = pre;  // 用新生成 node去连接上一个node, 生成的链表是逆序的
            carry = sum / 10;
            cur1 = cur1 != null ? cur1.next : null;
            cur2 = cur2 != null ? cur2.next : null;
        }

        // 打补丁(判断最后有没有进位)
        if (carry == 1) {
            pre = node;
            node = new ListNode(carry);
            node.next = pre;
        }
        return reverseList(node);
    }

    // 逆序链表
    public static ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }



    // Definition for singly-linked list.
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }


    public static void main(String[] args) {
        ListNode list1 = new ListNode(2);
        list1.next = new ListNode(4);
        list1.next.next = new ListNode(3);

        ListNode list2 = new ListNode(5);
        list2.next = new ListNode(6);
        list2.next.next = new ListNode(4);

//        addTwoNumbers(list1, list2);
    }
}
