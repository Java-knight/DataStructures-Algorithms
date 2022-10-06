package toplikedquestions;


import java.util.ArrayList;
import java.util.List;

// 求根节点到叶节点数字之和
// https://leetcode.cn/problems/sum-root-to-leaf-numbers/
public class Problem_0129_SumRootToLeafNumbers {

    // 思路: DFS
    int result = 0;
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        List<Integer> path = new ArrayList<>();
        path.add(root.val);
        process(path, root);
        return result;
    }

    public void process(List<Integer> path, TreeNode node) {
        if (node.left == null && node.right == null) {  // 收集答案(node是叶子节点)
            result += conversion(path);
        } else {
            if (node.left != null) {
                // 标记现场(左)
                path.add(node.left.val);
                // 进入下层决策(左)
                process(path, node.left);
                // 恢复现场(左)
                path.remove(path.size() - 1);
            }
            if (node.right != null) {
                // 标记现场(右)
                path.add(node.right.val);
                // 进入下层决策(右)
                process(path, node.right);
                // 恢复现场(右)
                path.remove(path.size() - 1);
            }
        }
    }

    // 将path中的数转 换为 整数 List[1,3,8]——>138
    public int conversion(List<Integer> path) {
        int cur = 0;
        for (int i = path.size() - 1, j = 0; i >= 0; i--, j++) {
            cur += path.get(i) * Math.pow(10, j);
        }
        return cur;
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
