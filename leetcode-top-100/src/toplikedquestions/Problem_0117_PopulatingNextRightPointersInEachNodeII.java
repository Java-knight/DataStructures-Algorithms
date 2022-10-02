package toplikedquestions;


import java.util.LinkedList;
import java.util.Queue;

// 填充每个节点的下一次右侧节点指针II
// https://leetcode.cn/problems/populating-next-right-pointers-in-each-node-ii/
public class Problem_0117_PopulatingNextRightPointersInEachNodeII {

    // 层序遍历
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        Node pre = null;
        int size = 0;
        while (!queue.isEmpty()) {
            pre = null;
            size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (pre != null) {   // 非第一次来到
                    pre.next = node;
                    pre = pre.next;
                } else {  // 第一次来到
                    pre = node;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return root;
    }

//    // 自己实现一个queue, 空间复杂度O(1); 刚好这个Node是有next的, 所以可以使用两个引用直接做一个队列
//    public Node connect(Node root) {
//        if (root == null) {
//            return null;
//        }
//
//        MyQueue queue = new MyQueue();
//        queue.offer(root);
//        int size = 0;
//        Node pre = null;
//        while (!queue.isEmpty()) {
//            size = queue.size;
//            pre = null;
//            for (int i = 0; i < size; i++) {
//                Node node = queue.poll();
//                if (pre != null) {  // 非第一次来到
//                    pre.next = node;
//                    pre = pre.next;
//                } else {  // 第一次来到
//                    pre = node;
//                }
//                if (node.left != null) {
//                    queue.offer(node.left);
//                }
//                if (node.right != null) {
//                    queue.offer(node.right);
//                }
//            }
//        }
//        return root;
//    }
//
//    public class MyQueue {
//        public Node head;
//        public Node tail;
//        public int size;
//
//        public MyQueue() {
//            this.head = null;
//            this.tail = null;
//            this.size = 0;
//        }
//
//        public void offer(Node val) {
//            size++;
//            if (head == null) {
//                head = val;
//                tail = val;
//            } else {
//                tail.next = val;
//                tail = tail.next;
//            }
//        }
//
//        public Node poll() {
//            size--;
//            Node node = head;
//            head = head.next;
//            node.next = null;
//            return node;
//        }
//
//        public boolean isEmpty() {
//            return this.size == 0;
//        }
//    }
}

