package toplikedquestions;


// 最长同值路径
// https://leetcode.cn/problems/longest-univalue-path/
public class Problem_0687_LongestUnivaluePath {

    // 二叉树的递归套路思路: x无关、x相关
    // x无关:
    // (1) 左子树的最大路径;  此时, max = 左子树的最大路径
    // (2) 右子树的最大路径;  此时, max = 右子树的最大路径
    // x有关:
    // (1) 左子树和右子树都没有最大路径（x是叶子节点）, max = 1
    // (2) left.val == node.val && node.val != right.val 此时, max = 左子树的以left开始的最大路径 + 1
    // (3) left.val != node.val && node.val == right.val 此时, max = 右子树的以right开始的最大路径 + 1
    // (4) left.val == node.val && node.val == right.val 此时, max = 右子树的以right开始的最大路径 + 左子树的以left开始的最大路径 + 1
    // 结论: 要获取x节点的最大路径, 需要 左/右子树的最大路径(max), 左/右子树的以left/right开始的最大路径(len)。

    int max = Integer.MIN_VALUE;  // 整棵树上的最大路径

    public int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        process(root);
        return max - 1;
    }

    // @Return: 左/右子树的以left/right开始的最大路径(len)
    public int process(TreeNode node) {
        if (node == null) {
            return 0;
        }
        TreeNode left = node.left;
        TreeNode right = node.right;
        int leftLen = process(left);
        int rightLen = process(right);
        int len = 1;
        if (left != null && left.val == node.val) {
            len = Math.max(len, leftLen + 1);
        }
        if (right != null && right.val == node.val) {
            len = Math.max(len, rightLen + 1);
        }

        max = Math.max(max, len);
        if (left != null && right != null && left.val == node.val && right.val == node.val) {
            max = Math.max(max, leftLen + rightLen + 1);
        }
        return len;
    }

//    // base case1
//    public int longestUnivaluePath(TreeNode root) {
//        if (root == null) {
//            return 0;
//        }
//        return process(root).max - 1;
//    }
//
//    // 假设以x节点为头的数, 返回两个信息
//    public class Info {
//        int len;  // 路径必须以x出发, 整棵树的合法路径最大距离
//        int max;  // 不要求路径必须以x出发, 整棵树的合法路径最大距离
//
//        public Info(int l, int m) {
//            this.len = l;
//            this.max = m;
//        }
//    }
//
//    public Info process(TreeNode node) {
//        if (node == null) {
//            return new Info(0, 0);
//        }
//        TreeNode left = node.left;
//        TreeNode right = node.right;
//        Info leftInfo = process(left);
//        Info rightInfo = process(right);
//
//        int len = 1;
//        if (left != null && left.val == node.val) {
//            len = leftInfo.len + 1;
//        }
//        if (right != null && right.val == node.val) {
//            len = Math.max(len, rightInfo.len + 1);
//        }
//        int max = Math.max(Math.max(leftInfo.max, rightInfo.max), len);
//        if (left != null && right != null && left.val == node.val && right.val == node.val) {
//            max = Math.max(max, leftInfo.max + rightInfo.max + 1);
//        }
//        return new Info(len, max);
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
