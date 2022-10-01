package toplikedquestions;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// 二叉树的中序遍历
// https://leetcode.cn/problems/binary-tree-inorder-traversal/
public class Problem_0094_BinaryTreeInorderTraversal {

    // Morris遍历细节
    // (1) 如果cur没有左孩子, cur向右移动(cur = cur.right)
    // (2) 如果cur有左孩子, 找到左子树上最右的节点mostRight:
    //     a: 如果mostRight的右指针指向空, 让其指想cur, 然后cur向左移动(cur = cur.left)
    //     b: 如果mostRight的右指针指向cur, 让其指向null, 然后cur向右移动(cur = cur.right)
    // (3) cur为空停止遍历

    // 方法3: Morris遍历(时间复杂度O(N), 空间复杂度O(1))—>空间准确来说应该是O(2N)
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode cur = root;
        TreeNode mostRight = cur;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {  // 第一次访问(连线)
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {  // 第二次访问(断线)
                    mostRight.right = null;
                }
            }
            result.add(cur.val);
            cur = cur.right;
        }
        return result;
    }

//    // 方法1: 递归版本(时间复杂度O(N), 空间复杂度O(H))
//    public List<Integer> inorderTraversal(TreeNode root) {
//        List<Integer> result = new ArrayList<>();
//        process(result, root);
//        return result;
//    }
//
//    public void process(List<Integer> list, TreeNode node) {
//        if (node == null) {
//            return;
//        }
//        process(list, node.left);
//        list.add(node.val);
//        process(list, node.right);
//    }

//    // 方法2: 迭代版本(时间复杂度O(N), 空间复杂度O(H))
//    public List<Integer> inorderTraversal(TreeNode root) {
//        List<Integer> result = new ArrayList<>();
//        Stack<TreeNode> stack = new Stack<>();
//        TreeNode cur = root;
//        while (!stack.isEmpty() || cur != null) {
//            if (cur != null) {
//                stack.push(cur);
//                cur = cur.left;
//            } else {
//                cur = stack.pop();
//                result.add(cur.val);
//                cur = cur.right;
//            }
//        }
//        return result;
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
