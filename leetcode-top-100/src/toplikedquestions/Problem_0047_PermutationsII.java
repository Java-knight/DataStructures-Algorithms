package toplikedquestions;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 全排列II
// https://leetcode.cn/problems/permutations-ii/
public class Problem_0047_PermutationsII {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        process(result, 0, nums);
        return result;
    }

    public void process(List<List<Integer>> result, int index, int[] nums) {
        if (index == nums.length) {  // 收集答案
            List<Integer> cur = new ArrayList<>();
            for (int num : nums) {
                cur.add(num);
            }
            result.add(cur);
        } else {
            Set<Integer> set = new HashSet<>();
            for (int i = index; i < nums.length; i++) {
                if (!set.contains(nums[i])) {
                    set.add(nums[i]);
                    // 标记现场
                    swap(nums, i, index);
                    // 进入下层决策
                    process(result, index + 1, nums);
                    // 恢复现场
                    swap(nums, i, index);
                }
            }
        }
    }

    public void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
