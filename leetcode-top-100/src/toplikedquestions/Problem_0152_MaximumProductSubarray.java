package toplikedquestions;


// 乘积最大子数组
// https://leetcode.cn/problems/maximum-product-subarray/
public class Problem_0152_MaximumProductSubarray {


    /**
     * 思路: 数组, 从[0, i]位置依次遍历(i就是从 0~len-1)
     * 注: 就是一个db题, 计算[0, i]位置的结果, 需要知道[0, i-1]位置上的结果
     *
     * 大流程：
     * 1. 只有nums[i]这个数
     * 2. 可能有nums[i]这个数
     * (2.1) 从[0, i-1]位置上的最大数
     * (2.2) 从[0, i-1]位置上最大数 * nums[i]
     * 注: 可能出现负数, 有负负得正的行为
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        int result = nums[0];
        int min = nums[0];  // 保存[0, i-1]位置上的最小值
        int max = nums[0];  // 保存[0, i-1]位置上的最大值
        for (int i = 1; i < nums.length; i++) {
            int curMin = Math.min(nums[i], Math.min(min * nums[i], max * nums[i]));
            int curMax = Math.max(nums[i], Math.max(min * nums[i], max * nums[i]));
            min = curMin;
            max = curMax;
            result = Math.max(result, max);
        }
        return result;
    }
}
