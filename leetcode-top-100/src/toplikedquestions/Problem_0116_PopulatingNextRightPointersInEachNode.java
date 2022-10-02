package toplikedquestions;


import java.util.LinkedList;
import java.util.Queue;

// 填充每个节点的下一个右侧节点指针
// https://leetcode.cn/problems/populating-next-right-pointers-in-each-node/
public class Problem_0116_PopulatingNextRightPointersInEachNode {

    // 层序遍历
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int size = 0;
        Node pre = null;
        while (!queue.isEmpty()) {
            size = queue.size();
            pre = null;
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (pre != null) {  // 非第一次来到
                    pre.next = node;
                    pre = node;
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
//        Node pre = null;
//        int size = 0;
//        MyQueue queue = new MyQueue();
//        queue.offer(root);
//        while (!queue.isEmpty()) {
//            size = queue.size;
//            pre = null;
//            for (int i = 0; i < size; i++) {
//                Node node = queue.poll();
//                if (pre == null) {
//                    pre = node;
//                } else {
//                    pre.next = node;
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
//            head = null;
//            tail = null;
//            size = 0;
//        }
//
//        public void offer(Node val) {
//            size++;
//            if (head == null) {
//                head = val;
//                tail = val;
//            } else {
//                tail.next = val;
//                tail = val;
//            }
//        }
//
//        public Node poll() {
//            size--;
//            Node node = head;
//            head = head.next;
//            node.next = null;  // 断开连接
//            return node;
//        }
//
//        public boolean isEmpty() {
//            return size == 0;
//        }
//    }
}

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
