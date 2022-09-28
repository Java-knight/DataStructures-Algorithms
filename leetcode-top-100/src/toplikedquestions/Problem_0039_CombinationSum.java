package toplikedquestions;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 组合总和
// https://leetcode.cn/problems/combination-sum/
public class Problem_0039_CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Arrays.sort(candidates);
        process(result, path, candidates, target, 0);
        return result;
    }

    public void process(List<List<Integer>> result, List<Integer> path, int[] candidates, int target, int index) {
        if (0 == target) {  // 收集答案
            result.add(new ArrayList<>(path));
        } else {
            for (int i = index; i < candidates.length; i++) {
                if (target >= candidates[i]) {
                    // 标记现场
                    path.add(candidates[i]);
                    // 进入下层决策
                    process(result, path, candidates, target - candidates[i],  i);
                    // 恢复现场
                    path.remove(path.size() - 1);
                }
            }
        }
    }
}
