package toplikedquestions;


// 将有序数组转换为二叉搜索树
// https://leetcode.cn/problems/convert-sorted-array-to-binary-search-tree/
public class Problem_0108_ConvertSortedArrayToBinarySearchTree {

    // 思路: 二分查找的可以尽可能的让它平衡
    public TreeNode sortedArrayToBST(int[] nums) {
        return process(nums, 0, nums.length - 1);
    }

    public TreeNode process(int[] nums, int left, int right) {
        if (left > right) {  // base case1 越界(叶子节点)
            return null;
        }
        if (left == right) {  // base case2 等于的时候遍历完了, 直接创建一个叶子节点
            return new TreeNode(nums[left]);
        }

        // base case3 nums[mid]位置创建一个node, 继续左右连边一起创建
        int mid = left + ((right - left) >> 1);
        TreeNode node = new TreeNode(nums[mid]);
        node.left = process(nums, left, mid - 1);
        node.right = process(nums, mid + 1, right);
        return node;
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
