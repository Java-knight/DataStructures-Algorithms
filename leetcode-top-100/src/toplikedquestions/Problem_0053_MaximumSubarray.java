package toplikedquestions;


// 最大子数组和
// https://leetcode.cn/problems/maximum-subarray/
public class Problem_0053_MaximumSubarray {

    // 子数组: 是数组中的一个连续部分
    // 动态规划(空间优化)
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int max = Integer.MIN_VALUE;
        int cur = 0;
        for (int i = 0; i < nums.length; i++) {
            cur += nums[i];
            max = Math.max(max, cur);  // 收集结果
            cur = cur < 0 ? 0 : cur;  // 我加了nums[i]位置的数还导致我变成了负数, 那我就不能加, 从0开始
        }
        return max;
    }


//    // 方法1: 动态规划
//    public int maxSubArray(int[] nums) {
//        if (nums == null || nums.length == 0) {
//            return -1;
//        }
//        int[] dp = new int[nums.length];
//        dp[0] = nums[0];
//        int max = dp[0];
//        for (int i = 1; i < nums.length; i++) {
//            int case1 = nums[i];  // base case1 只有当前i位置的数
//            int case2 = nums[i] + dp[i - 1];  // base case2 当前i位置的数+i-1位置上的最大值
//            dp[i] = Math.max(case1, case2);
//            max = Math.max(max, dp[i]);  // 收集最优解的
//        }
//        return max;
//    }
}
