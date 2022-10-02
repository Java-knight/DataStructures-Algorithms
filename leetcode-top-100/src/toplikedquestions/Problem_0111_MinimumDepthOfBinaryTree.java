package toplikedquestions;


import java.util.LinkedList;
import java.util.Queue;

// 二叉树的最小深度
// https://leetcode.cn/problems/minimum-depth-of-binary-tree/
public class Problem_0111_MinimumDepthOfBinaryTree {

    // BFS 实现(时间复杂度O(N), 空间复杂度O(N))
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int result = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int size = 0;
        while (!queue.isEmpty()) {
            size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null) {
                    return result;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result++;
        }
        return result;
    }

//    // 暴力递归
//    public int minDepth(TreeNode root) {
//        if (root == null) {  // base case1 当前节点走到了叶子节点的左右孩子
//            return 0;
//        }
//        // base case2 当前节点不是null, 如果左子树或右子树最小高度为0, 表示当前node是叶子节点
//        int left = minDepth(root.left);
//        int right = minDepth(root.right);
//        if (left == 0 || right == 0) {
//            return left + right + 1;
//        }
//        // base case3 当前节点是非叶子节点, 同时有左右孩子
//        return Math.min(left, right) + 1;
//    }

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
