package toplikedquestions;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 1:11
// 四数之和
// https://leetcode.cn/problems/4sum/
public class Problem_0018_4Sum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        int n = 4;
        return nSum(nums, 0, target, n);
    }

    /**
     * 求nSum之和, nums必须是有序的, begin 是开始位置, n是当前求的n个数之和为target
     * 递归调用, 只有在twoSum的时候需要使用双指针
     * @param nums
     * @param begin
     * @param target
     * @param n
     * @return
     */
    public List<List<Integer>> nSum(int[] nums, int begin, int target, int n) {
        if (n == 2) {
            return towSum(nums, begin, target);
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int i = begin; i < nums.length - n + 1; i++) {
            if (i == begin || nums[i - 1] != nums[i]) {
                if (isSubtractOverflow(target, nums[i])) {  // leetcode新加的溢出case, 判断是否发生溢出
                    return result;
                }
                List<List<Integer>> nexts = nSum(nums, i + 1, target-nums[i], n - 1);
                for (List<Integer> cur : nexts) {
                    cur.add(0, nums[i]);
                    result.add(cur);
                }
            }
        }
        return result;
    }

    public List<List<Integer>> towSum(int[] nums, int begin, int target) {
        int left = begin;
        int right = nums.length - 1;
        List<List<Integer>> result = new ArrayList<>();
        while (left < right) {
            if (nums[left] + nums[right] > target) {
                right--;
            } else if (nums[left] + nums[right] < target) {
                left++;
            } else {  // 需要判断 nums[left] != nums[left-1]
                if (left == begin || nums[left - 1] != nums[left]) {
                    List<Integer> cur = new ArrayList<>();
                    cur.add(nums[left]);
                    cur.add(nums[right]);
                    result.add(cur);
                }
                left++;
            }
        }
        return result;
    }

    // 判断是否发生溢出, 仿照Math.subtractExact()改造的
    public boolean isSubtractOverflow(int x, int y) {
        int r = x - y;
        if (((x ^ y) & (x ^ r)) < 0) {  // 溢出
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Problem_0018_4Sum demo = new Problem_0018_4Sum();
        int[] arr = {2, 2, 2, 2, 2};
        System.out.println(demo.fourSum(arr, 8));
    }
}
