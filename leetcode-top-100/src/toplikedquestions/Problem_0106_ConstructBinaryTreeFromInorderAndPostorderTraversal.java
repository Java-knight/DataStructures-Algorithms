package toplikedquestions;


import java.util.HashMap;
import java.util.Map;

// 从中序和后续遍历构造二叉树
// https://leetcode.cn/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
public class Problem_0106_ConstructBinaryTreeFromInorderAndPostorderTraversal {
    // 使用map表做一点加速
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return process(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1, map);
    }

    public TreeNode process(int[] postorder, int l1, int r1, int[] inorder, int l2, int r2, Map<Integer, Integer> map) {
        if (l1 > r1) {  // base case1 叶子节点的左右孩子
            return null;
        }
        TreeNode node = new TreeNode(postorder[r1]);
        if (l1 == r1) {  // base case2 叶子节点
            return node;
        }

        // base case3 非叶子节点, 在inorder[findIndex] == postorder[r1]
        int findIndex = map.get(postorder[r2]);
        node.left = process(postorder, l1, l1 + findIndex - l2 - 1, inorder, l2, findIndex - 1, map);
        node.right = process(postorder, l1 + findIndex - l2, r1 - 1, inorder, findIndex + 1, r2, map);
        return node;
    }

//    // 暴力递归(画图, 坐标变化)
//    public TreeNode buildTree(int[] inorder, int[] postorder) {
//        return process(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1);
//    }
//
//    public TreeNode process(int[] postorder, int l1, int r1, int[] inorder, int l2, int r2) {
//        if (l1 > r1) {  // base case1 叶子节点的左右孩子
//            return null;
//        }
//        TreeNode node = new TreeNode(postorder[r1]);
//        if (l1 == r1) {  // base case2 叶子节点
//            return node;
//        }
//
//        // base case3 非叶子节点, 在inorder[findIndex] == postorder[r1]
//        int findIndex = l2;
//        for (; findIndex < r2; findIndex++) {
//            if (inorder[findIndex] == postorder[r1]) {
//                break;
//            }
//        }
//        node.left = process(postorder, l1, l1 + findIndex - l2 - 1, inorder, l2, findIndex - 1);
//        node.right = process(postorder, l1 + findIndex - l2, r1 - 1, inorder, findIndex + 1, r2);
//        return node;
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
