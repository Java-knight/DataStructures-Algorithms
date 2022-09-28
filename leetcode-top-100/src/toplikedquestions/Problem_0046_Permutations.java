package toplikedquestions;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

// 全排列
// https://leetcode.cn/problems/permutations/
public class Problem_0046_Permutations {

    public List<List<Integer>> permute(int[] nums) {
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
            for (int i = index; i < nums.length; i++) {
                // 保存现场
                swap(nums, i, index);
                // 进入下层决策
                process(result, index + 1, nums);
                // 恢复现场
                swap(nums, i, index);
            }
        }
    }

    public void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
