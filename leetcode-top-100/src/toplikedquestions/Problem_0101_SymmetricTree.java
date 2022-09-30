package toplikedquestions;



// 对称二叉树
// https://leetcode.cn/problems/symmetric-tree/
public class Problem_0101_SymmetricTree {

    public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }

    // 一棵树是原始树   node1
    // 另一棵树是翻面树 node2
    public boolean isMirror(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 != null && node2 != null) {
            return node1.val == node2.val &&
                    isMirror(node1.left, node1.right) &&
                    isMirror(node2.right, node2.left);
        }
        return false;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) {this.val = val;}
        TreeNode(int val, TreeNode left, TreeNode right) {this.val = val;this.left = left;this.right = right;}
    }


}
