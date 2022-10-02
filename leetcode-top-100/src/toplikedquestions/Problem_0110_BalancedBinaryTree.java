package toplikedquestions;


// 平衡二叉树
// https://leetcode.cn/problems/balanced-binary-tree/
public class Problem_0110_BalancedBinaryTree {

    boolean flag;
    public boolean isBalanced(TreeNode root) {
        flag = true;  // 表示以当前节点出发的树是否是平衡二叉树
        process(root);
        return flag;
    }

    // 返回的是当前node的最大树高
    public int process(TreeNode node) {
        if (!flag || node == null) {  // 已经不平衡了或 走到了叶子节点的左右孩子
            return 0;
        }
        int leftHeight = process(node.left);
        int rightHeight = process(node.right);
        // 更新当前node是否平衡
        if (Math.abs(leftHeight - rightHeight) > 1) {
            flag = false;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

//    // 思路: 判断每个节点的左右两个子树的最大高度差的绝对值是否 <= 1, 并且左右子树也要满足
//    // 这种方法, 不好有太多的重复计算了, 多次递归重复求二叉树高度
//    public boolean isBalanced(TreeNode root) {
//        if (root == null) {
//            return true;
//        }
//        return Math.abs(process(root.left) - process(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
//    }
//
//    // 求二叉树的最大树高
//    public int process(TreeNode node) {
//        if (node == null) {  // 叶子节点的左右孩子节点
//            return 0;
//        }
//        return Math.max(process(node.left), process(node.right)) + 1;
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


