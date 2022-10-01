package toplikedquestions;


import java.util.Stack;

// 验证二叉搜索树
// https://leetcode.cn/problems/validate-binary-search-tree/
public class Problem_0098_ValidateBinarySearchTree {

    // 方法3: Morris遍历(时间复杂度O(N), 空间复杂度O(1))—>空间准确来说应该是O(2N)
    public boolean isValidBST(TreeNode root) {
        TreeNode cur = root;
        TreeNode mostRight = null;
        TreeNode pre = null;
        boolean flag = true;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {  // 第一次访问
                    pre = mostRight;
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {  // 第二次访问
                    mostRight.right = null;
                }
            }
            if (pre != null && pre.val >= cur.val) {
                flag = false;
            }
            cur = cur.right;
        }
        return flag;
    }

//    // 方法1: 递归版本(时间复杂度O(N), 空间复杂度O(H))
//    // 注意: 这里的最大最小值都是使用Long类型, 因为参数中会有Integer.MAX_VALUE, 当系统最大, 需要返回true
//    public boolean isValidBST(TreeNode root) {
//        return process(root, Long.MIN_VALUE, Long.MAX_VALUE);
//    }
//
//    public boolean process(TreeNode node, long min, long max) {
//        if (node == null) {
//            return true;
//        }
//        if (node.val <= min || node.val >= max) {
//            return false;
//        }
//        return process(node.left, min, node.val) && process(node.right, node.val, max);
//    }

//    // 方法2: 迭代版本(时间复杂度O(N), 空间复杂度O(H))
//    public boolean isValidBST(TreeNode root) {
//        long pre = Long.MIN_VALUE;
//        TreeNode cur = root;
//        Stack<TreeNode> stack = new Stack<>();
//        while (!stack.isEmpty() || cur != null) {
//            if (cur != null) {
//                stack.push(cur);
//                cur = cur.left;
//            } else {
//                cur = stack.pop();
//                if (pre >= cur.val) {
//                    return false;
//                }
//                pre = cur.val;
//                cur = cur.right;
//            }
//        }
//        return true;
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

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
    }
}
