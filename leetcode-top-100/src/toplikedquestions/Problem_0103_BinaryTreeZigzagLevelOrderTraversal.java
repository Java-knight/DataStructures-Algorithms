package toplikedquestions;

import java.util.*;

// 二叉树的锯齿层序遍历
// https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal/
public class Problem_0103_BinaryTreeZigzagLevelOrderTraversal {

    // 思路(画图): level从第0层开始, root直接加入;
    //      level到第1层, 先加左孩子, 再加右孩子, 头进尾出;
    //      level到第2层, 先加右孩子, 再加左孩子, 尾进头出; 接下来同理
    // 代码精简
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        boolean flag = true;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int size = 0;
        while (!queue.isEmpty()) {
            size = queue.size();
            List<Integer> cur = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = flag ? queue.poll() : queue.pollLast();
                cur.add(node.val);
                if (flag) {  // 先左后右, 尾进头出
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                } else {  // 先右后左, 头进尾出
                    if (node.right != null) {
                        queue.offerFirst(node.right);
                    }
                    if (node.left != null) {
                        queue.offerFirst(node.left);
                    }
                }
            }
            flag = !flag;
            result.add(cur);
        }
        return result;
    }

//    // 原始版本(和上面基本一样)
//    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
//        List<List<Integer>> result = new ArrayList<>();
//        if (root == null) {
//            return result;
//        }
//        boolean flag = true;
//        Deque<TreeNode> queue = new LinkedList<>();
//        queue.offer(root);
//        int size = 0;
//        while (!queue.isEmpty()) {
//            size = queue.size();
//            List<Integer> cur = new ArrayList<>();
//            if (flag) {  // 先左后右, 尾进头出
//                for (int i = 0; i < size; i++) {
//                    TreeNode node = queue.poll();
//                    cur.add(node.val);
//                    if (node.left != null) {
//                        queue.offer(node.left);
//                    }
//                    if (node.right != null) {
//                        queue.offer(node.right);
//                    }
//                }
//            } else {  // 先右后左, 头进尾出
//                for (int i = 0; i < size; i++) {
//                    TreeNode node = queue.pollLast();
//                    cur.add(node.val);
//                    if (node.right != null) {
//                        queue.offerFirst(node.right);
//                    }
//                    if (node.left != null) {
//                        queue.offerFirst(node.left);
//                    }
//                }
//            }
//            flag = !flag;
//            result.add(cur);
//        }
//        return result;
//    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
