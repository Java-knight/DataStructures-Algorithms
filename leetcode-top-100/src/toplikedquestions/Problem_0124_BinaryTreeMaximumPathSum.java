package toplikedquestions;


// 二叉树中的最大路径和
// https://leetcode.cn/problems/binary-tree-maximum-path-sum/
public class Problem_0124_BinaryTreeMaximumPathSum {

    // 思路: 按照x进行划分, x就是树上的某个node
    // base case1 x无关: (2种情况求max)
    //     a. leftTree(不含x, x的左子树的最大路径和)
    //     b. rightTree(不含x, x的右子树的最大路径和)
    // base case2 x有关: (4种情况求max)
    //     a. selfX的值(只包含x一个)
    //     b. leftPath, 以x为头向左子树连接
    //     c. rightPath, 以x为头向右子树连接
    //     d. leftRightPath, 以x为头向左子树和右子树都连接
    // result: 求base case1 和 base case2的max
    // 代码实现: 设计一个Info{result, maxPathHead}结构,
    // result就是6种情况的max, maxPathHead 就是base case2中a,b,c的max
    // 注意: 为啥maxPathHead 不是没有算base case2 的d, 因为两边都连接相当于head已经不是x节点了
    public int maxPathSum(TreeNode root) {
        return process(root).result;
    }

    public Info process(TreeNode x) {
        if (x == null) {  // 走到了空(递归结束)
            return null;
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        // base case1
        int leftTree = Integer.MIN_VALUE;
        if (leftInfo != null) {  // a 不含x, x的左子树的最大路径和
            leftTree = Math.max(leftTree, leftInfo.result);
        }
        int rightTree = Integer.MIN_VALUE;
        if (rightInfo != null) {  // b 不含x, x的右子树的最大路径和
            rightTree = Math.max(rightTree, rightInfo.result);
        }

        // base case2
        int selfX = x.val;  // a 只包含x一个
        int leftPath = Integer.MIN_VALUE;
        if (leftInfo != null) {  // b 以x为头向左子树连接
            leftPath = x.val + leftInfo.maxPathHead;
        }
        int rightPath = Integer.MIN_VALUE;
        if (rightInfo != null) {  // c 以x为头向右子树连接
            rightPath = x.val + rightInfo.maxPathHead;
        }
        int leftRightPath = Integer.MIN_VALUE;
        if (leftInfo != null && rightInfo != null) {  // d 以x为头向左子树和右子树都连接
            leftRightPath = x.val + leftInfo.maxPathHead + rightInfo.maxPathHead;
        }

        // 收接x节点的Info
        int maxPathHead = Math.max(Math.max(leftPath, rightPath), selfX);  // base case2 a,b,c
        int result = Math.max(  // max(base case1, base case2)
                Math.max(Math.max(leftTree, rightTree), leftRightPath),
                maxPathHead
        );

        return new Info(result, maxPathHead);
    }

    public class Info {
        public int result;
        public int maxPathHead;

        public Info(int result, int maxPathHead) {
            this.result = result;
            this.maxPathHead = maxPathHead;
        }
    }


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
