package class01;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/target-sum/
 * @author knight
 * @desc
 * @date 2023/12/16
 */
public class Code07_TargetSum {


    /**
     * 递归方法; 时间复杂度O(N^2)
     * 深度优先搜索(DFS), 将nums中的每个元素是+和-分别来一个分支[二叉树一直向下]
     */
    public static int findTargetSumWays1(int[] nums, int target) {
        return process1(nums, 0, target);
    }

    private static int process1(int[] nums, int index, int target) {
        if (index == nums.length) {  // 递归结束条件, target == 0表示这是一种方法
            return target == 0 ? 1 : 0;
        }
        // +和-的结果继续深搜
        return process1(nums, index + 1, target - nums[index]) + process1(nums, index + 1, target + nums[index]);
    }

    /**
     * 动态规划; 时间复杂度: O(N)
     * 搞一个 Map<index, Map<target, result>> 存储当前index位置的所有结果
     */
    public static int findTargetSumWays2(int[] nums, int target) {
        return process2(nums, 0, target, new HashMap<>());
    }

    private static int process2(int[] nums, int index, int target, Map<Integer, Map<Integer, Integer>> dp) {
        if (dp.containsKey(index) && dp.get(index).containsKey(target)) {  // 命中缓存
            return dp.get(index).get(target);
        }
        // 没有缓存, 计算, 存储结果
        int ans = 0;
        if (index == nums.length) {  // 找到结果
            ans = target == 0 ? 1 : 0;
        } else {
            ans = process2(nums, index + 1, target - nums[index], dp) + process2(nums, index + 1, target + nums[index], dp);
        }

        // 结果存储
        if (!dp.containsKey(index)) {
            dp.put(index, new HashMap<>());
        }
        dp.get(index).put(target, ans);
        return ans;
    }

    /**
     * 5大优化点+动态规划+空间压缩技巧; 时间复杂度: O(N)【这个常数项更小】
     * 优化1: 非负性
     *       可以认为 nums 中都是非负数, 因为即使nums 中有负数, 比如[3, -4, 2],
     *       因为题目就是要在每个数前面用 +/- 号, 所以[3, -4, 2]其实和[3, 4, 2]最终的结果是一样的
     * 优化2: target > sum
     *       如果 nums 都是非负数, 并且所有数的累加和都是sum.
     *       如果 target > sum, 很明显没有任何方法可以达到 target, 可以直接返回 0
     * 优化3: 奇偶性
     *       nums内部的数组, 不管怎么 +/-, 最终的结果都一定不会改变奇偶性,
     *       如果所有的数累加和sum与 target 的奇偶性不同, 没有任何方法可以达到 target, 可以直接返回0
     * 优化4: 推导式 -> sum(P) = (target + nums所有数累加和) / 2 -> target:sum(P)=1:1
     *       比如说给定一个数组, nums = [1, 2, 3, 4, 5] target = 3
     *       其中一种结果组合 +1 -2 +3 -4 +5 = 3. 该组合中取了正的集合 P = {1, 3, 5}; 该组合中取了负的集合 N = {2, 4}
     *       任何一种组合都满足: sum(P) - sum(N) = target
     *         推导[等式左右加上 sum(P) + sum(N)]: sum(P) - sum(N) + sum(P) + sum(N) = target + sum(P) + sum(N)
     *         2 * sum(P) = target + sum(P+N) ——> sum(P) = (target + nums所有数累加和) / 2
     *         结论: 任何一个集合, 只要累加和是(target + nums所有数累加和) / 2, 那么一定对应一种target的组合
     *       案例: 非负数组 nums, target=7. 而所有的数累加和是15, 结果就是求有多少种方法累加和是 (7+15)/2=11
     * 优化5: 动态规划技巧
     *       动态优化空间压缩技巧
     */
    public static int findTargetSumWays3(int[] nums, int target) {
        int sum = 0;
        for (int item : nums) {
            sum += item;
        }
        return sum < target || ((target & 1) ^ (sum & 1)) != 0 ? 0 : subset2(nums, (target + sum) >> 1);
    }

    /**
     * 动态规划, 状态转移方程: j就表示 positiveSum
     * dp[i][j] = dp[i - 1][j];
     * if (j - nums[i - 1] >= 0) {
     *     dp[i][j] += dp[i - 1][j - nums[i - 1]];
     * }
     * @param nums
     * @param positiveSum: nums数组中正数和
     * @return
     */
    private static int subset1(int[] nums, int positiveSum) {
        if (positiveSum < 0) {
            return 0;
        }

        int len = nums.length;
        // dp[i][j]: nums前缀为i的所有自己[0...i], 有多少累加和是j
        int[][] dp = new int[len + 1][positiveSum + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j <= positiveSum; j++) {
                dp[i][j] = dp[i - 1][j];  // 0...i —> 0...i-1 + 0...i-1中累加和j-nums[i]位置的上方法数(代码中dp是从1...len的, 所以获取nums[i]位置上的值写的是nums[i-1])
                if (j - nums[i - 1] >= 0) {
                    dp[i][j] += dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[len][positiveSum];
    }

    /**
     * 动态规划+空间压缩技巧
     * 为啥可以空间压缩?
     * 因为 dp[i][j]位置的值依赖于dp[i-1][j]和dp[i-1][j-nums[i-1]]位置上的值. 当前值只依赖于上一行的值, 每必要将整个二维表都保存
     * 注意: 从右向左填, 因为从左往右写, 前面的会被覆盖掉, 导致结果错误
     */
    private static int subset2(int[] nums, int positiveSum) {
        if (positiveSum < 0) {
            return 0;
        }
        int len = nums.length;
        int[] dp = new int[positiveSum + 1];
        dp[0] = 1;
        for (int num : nums) {
            for (int i = positiveSum; i >= num; i--) {
                dp[i] += dp[i - num];
            }
        }
        return dp[positiveSum];
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 1, 1};
        int res = findTargetSumWays3(arr, 3);
        System.out.println(res);
    }
}
