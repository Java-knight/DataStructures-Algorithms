package toplikedquestions;


import java.util.HashMap;
import java.util.Map;

// 从前序与中序遍历序列构造二叉树
// https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
public class Problem_0105_ConstructBinaryTreeFromPreorderAndInorderTraversal {
    // 使用map表做一点加速
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return process(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);
    }

    // 在 preorder[l1, r1]和inorder[l2, r2]上构造树
    public TreeNode process(int[] preorder, int l1, int r1, int[] inorder, int l2, int r2, Map<Integer, Integer> map) {
        if (l1 > r1) {  // base case1 越界
            return null;
        }
        TreeNode node = new TreeNode(preorder[l1]);
        if (l1 == r1) {  // base case2 已经找完了
            return node;
        }

        // base case3 inorder上还有值, 找下preorder[l1] = inorder[findIndex]
        // 在 inorder 上找到 preorder[l1] = inorder[findIndex]
        int findIndex = map.get(preorder[l1]);
        for (; findIndex <= r2; findIndex++) {
            if (inorder[findIndex] == preorder[l1]) {
                break;
            }
        }
        node.left = process(preorder, l1 + 1, r1 - r2 + findIndex, inorder, l2, findIndex - 1, map);
        node.right = process(preorder, r1 - r2 + findIndex + 1, r1, inorder, findIndex + 1, r2, map);
        return node;
    }


//    // 暴力递归
//    public TreeNode buildTree(int[] preorder, int[] inorder) {
//        return process(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
//    }
//
//    // 在 preorder[l1, r1]和inorder[l2, r2]上构造树
//    public TreeNode process(int[] preorder, int l1, int r1, int[] inorder, int l2, int r2) {
    //        if (l1 > r1) {  // base case1 越界
//            return null;
//        }
//        TreeNode node = new TreeNode(preorder[l1]);
//        if (l1 == r1) {  // base case2 已经找完了
//            return node;
//        }
//
//        // base case3 inorder上还有值, 找下preorder[l1] = inorder[findIndex]
//        // 在 inorder 上找到 preorder[l1] = inorder[findIndex]
//        int findIndex = l2;
//        for (; findIndex <= r2; findIndex++) {
//            if (inorder[findIndex] == preorder[l1]) {
//                break;
//            }
//        }
//        node.left = process(preorder, l1 + 1, r1 - r2 + findIndex, inorder, l2, findIndex - 1);
//        node.right = process(preorder,r1 - r2 + findIndex + 1, r1,  inorder, findIndex + 1, r2);
//        return node;
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
