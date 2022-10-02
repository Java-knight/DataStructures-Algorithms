package toplikedquestions;

import java.util.*;

// 二叉树的层序遍历II
// https://leetcode.cn/problems/binary-tree-level-order-traversal-ii/
public class Problem_0107_BinaryTreeLevelOrderTraversalII {

    // 问题: 从底向上遍历
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int size = 0;
        while (!queue.isEmpty()) {
            size = queue.size();
            List<Integer> cur = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                cur.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(0, cur);  // 每次都头插进入, 最后得到的list结果就是最底向上
        }
        return result;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) {this.val = val;}
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}


