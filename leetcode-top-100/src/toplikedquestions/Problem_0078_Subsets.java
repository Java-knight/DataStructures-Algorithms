package toplikedquestions;


import java.util.ArrayList;
import java.util.List;

// 子集
// https://leetcode.cn/problems/subsets/
public class Problem_0078_Subsets  {

    // 思路: 一个简单的暴力递归, 每个数分为要 和 不要, 叶子节点收集答案
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        process(result, path, 0, nums);
        return result;
    }

    public void process(List<List<Integer>> result, List<Integer> path, int index, int[] nums) {
        if (index == nums.length) {  // 收集答案
            result.add(new ArrayList<>(path));
        } else {
            // 【不要index位置的数】
            // 标记现场(不要当前这个数, 所以不需要标记现场)
            // 进入下层决策
            process(result, path, index + 1, nums);
            // 恢复现场(因为没有标记现场, 也不需要恢复)

            // 【要index位置的数】
            // 标记现场(要当前这个数, 所以需要标记现场)
            path.add(nums[index]);
            // 进入下层决策
            process(result, path, index + 1, nums);
            // 恢复现场
            path.remove(path.size() - 1);
        }
    }
}
