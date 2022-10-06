package toplikedquestions;


// 复制带随机指针的链表
// https://leetcode.cn/problems/copy-list-with-random-pointer/
public class Problem_0138_CopyListWithRandomPointer {

    // 思路: 将老链表每个节点后面都赋值一份, 设置好random指针, 进行拆分
    // https://www.processon.com/view/link/633e81ba6376891c6b42067e
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        Node next = null;
        // (1) 复制链表 1—>2—>3—>4    1—>1'—>2—>2'—>3—>3'—>4—>4'
        while (cur != null) {
            next = cur.next;
            cur.next = new Node(cur.val);
            cur.next.next = next;
            cur = next;
        }
        // (2) 给复制的节点rand指针赋值
        cur = head;
        Node copyCur = null;
        while (cur != null) {
            next = cur.next.next;
            copyCur = cur.next;
            copyCur.random = cur.random != null ? cur.random.next : null;
            cur = next;
        }
        // (3) 拆链表
        Node result = head.next;
        cur = head;
        while (cur != null) {
            next = cur.next.next;
            copyCur = cur.next;
            cur.next = next;
            copyCur.next = next != null ? next.next : null;
            cur = next;
        }
        return result;
    }


    public class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}

