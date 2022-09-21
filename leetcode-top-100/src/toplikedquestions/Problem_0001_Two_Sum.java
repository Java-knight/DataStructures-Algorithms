package toplikedquestions;

import java.util.HashMap;
import java.util.Map;

// 两数之和
// https://leetcode.cn/problems/two-sum/?favorite=2cktkvj
public class Problem_0001_Two_Sum {

    public int[] twoSum(int[] nums, int target) {
        // key nums中的数，value表示这个数的下标
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {

    }
}
