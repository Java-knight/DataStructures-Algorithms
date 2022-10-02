package toplikedquestions;


// 有序链表转换搜索二叉树
// https://leetcode.cn/problems/convert-sorted-list-to-binary-search-tree/
public class Problem_0109_ConvertSortedListToBinarySearchTree {

    // 思路: 使用二分法, 将list转换为 array
    public TreeNode sortedListToBST(ListNode head) {
        // list转array
        int size = 0;
        ListNode cur = head;
        while (cur != null) {
            size++;
            cur = cur.next;
        }

        int[] arr = new int[size];
        cur = head;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = cur.val;
            cur = cur.next;
        }
        return process(arr, 0, arr.length - 1);
    }

    public TreeNode process(int[] arr, int left, int right) {
        if (left > right) {  // base case1 叶子节点的所有孩子
            return null;
        }
        // base case2 叶子/非叶子节点
        int mid = left + ((right - left) >> 1);
        TreeNode node = new TreeNode(arr[mid]);
        node.left = process(arr, left, mid - 1);
        node.right = process(arr, mid + 1, right);
        return node;
    }
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) {this.val = val;}
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
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
