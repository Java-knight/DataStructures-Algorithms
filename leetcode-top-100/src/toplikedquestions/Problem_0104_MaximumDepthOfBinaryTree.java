package toplikedquestions;


// 二叉树的最大深度
// https://leetcode.cn/problems/maximum-depth-of-binary-tree/
public class Problem_0104_MaximumDepthOfBinaryTree {

    public int maxDepth(TreeNode root) {
        if (root == null) {  // 叶子节点 或 跟节点为空
            return 0;
        }
        if (root.left == null && root.right == null) {  // 这个可以不加, 左右两个孩子是叶子节点
            return 1;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
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
