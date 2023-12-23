package class02;

/**
 * 581. 最短无序连续子数组
 * https://leetcode.cn/problems/shortest-unsorted-continuous-subarray/
 * @desc
 * @author knight
 * @date 2023/12/23
 */
public class Code06_MinLengthForSort {

    /**
     * 从左到右找到右边界, 从右向左找到左边界
     * 时间复杂度: O(N), 空间复杂度: O(1)
     */
    public static int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int len = nums.length;

        // 从左到右来一编, 找右边界
        int right = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            if (max > nums[i]) {  // 0...i位置上i这个数就是右边界
                right = i;
            }
            max = Math.max(max, nums[i]);
        }

        // 从右到左来一编, 找左边界
        int min = Integer.MAX_VALUE;
        int left = len;
        for (int i = len - 1; i >= 0; i--) {
            if (min < nums[i]) {
                left = i;
            }
            min = Math.min(min, nums[i]);
        }

        return Math.max(0, right - left + 1);
    }

}
