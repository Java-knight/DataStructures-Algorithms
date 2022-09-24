package toplikedquestions;

import com.sun.org.apache.bcel.internal.generic.LUSHR;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 三数之和
// https://leetcode.cn/problems/3sum/
public class Problem_0015_3Sum {

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        ArrayList<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || nums[i - 1] != nums[i]) {
                List<List<Integer>> nexts = towSum(nums, i + 1, -nums[i]);
                for (List<Integer> cur : nexts) {
                    cur.add(0, nums[i]);
                    result.add(cur);
                }
            }
        }
        return result;
    }

    // nums[begin....end] 范围上
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
}
