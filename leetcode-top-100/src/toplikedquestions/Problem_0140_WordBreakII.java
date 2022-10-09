package toplikedquestions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 单词拆分II
// https://leetcode.cn/problems/word-break-ii/
public class Problem_0140_WordBreakII {

    // 思路: dfs暴力递归, 也可以dp/dp+前缀树, 但是收集答案还是要dfs, 所以没必要
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        List<String> result = new ArrayList<>();
        List<String> path = new ArrayList<>();
        process(result, path, 0, s, dict);
        return result;
    }

    public void process(List<String> result, List<String> path, int index, String str, Set<String> dict) {
        if (index == str.length()) {  // 收集答案
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < path.size() - 1; i++) {
                sb.append(path.get(i)).append(" ");
            }
            sb.append(path.get(path.size() - 1));
            result.add(sb.toString());
        } else {
            for (int end = index; end < str.length(); end++) {
                String pre = str.substring(index, end + 1);
                if (dict.contains(pre)) {
                    // 标记现场
                    path.add(pre);
                    // 进入下层决策
                    process(result, path, end + 1, str, dict);
                    // 恢复现场
                    path.remove(path.size() - 1);
                }
            }
        }
    }
}
