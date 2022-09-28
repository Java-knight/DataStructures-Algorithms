package toplikedquestions;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 组合总和II
// https://leetcode.cn/problems/combination-sum-ii/
public class Problem_0040_CombinationSumII {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Arrays.sort(candidates);
        process(result, path, candidates, target, 0);
        return result;
    }

    public void process(List<List<Integer>> result, List<Integer> path, int[] candidates, int target, int index) {
        if (target == 0) {  // 收集答案
            result.add(new ArrayList<>(path));
        } else {
            for (int i = index; i < candidates.length; i++) {
                if (target < candidates[i]) {  // 剪支优化
                    break;
                }
                if (i > index && candidates[i - 1] == candidates[i]) {  // 去重
                    continue;
                }
                // 标记现场
                path.add(candidates[i]);
                // 进入下层决策
                process(result, path, candidates, target - candidates[i],  i + 1);
                // 恢复现场
                path.remove(path.size() - 1);
            }
        }
    }
}
