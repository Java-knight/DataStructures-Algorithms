package toplikedquestions;


import java.util.ArrayList;
import java.util.List;

// 分隔回文串
// https://leetcode.cn/problems/palindrome-partitioning/
public class Problem_0131_PalindromePartitioning {

    // 思路: 递归写法, 加入s = "abcabc"是否是回文
    // 比如: "a", "bcabc";   "ab", "cabc";   "abc", "abc";
    //      "abca", "bc";   "abcab", "c";   "abcabc", "";
    // str[L~R]是否是回文: (1) str[L] == str[R] (2) str[L+1, R-1]是否回文串
    // dp表的设计: 直接使用一张bool二维dp表就可以完成, dp[i][j]就表示str[L, R]是否是回文,
    //           dp表的左下部分不用(i>j -> L>R), dp表的初始化dp[i][i+1] = str[i] == str[i+1]
    //           其它就是dp[i][j] = dp[i+1][j-1] && dp[i][j]
    public List<List<String>> partition(String s) {
        boolean[][] dp = getDP(s.toCharArray());
        List<List<String>> result = new ArrayList<>();
        List<String> path = new ArrayList<>();
        process(result, path, s, 0, dp);
        return result;
    }

    // 获取判单str[L, R]中是否是回文串的dp表
    public boolean[][] getDP(char[] str) {
        int size = str.length;
        boolean[][] dp = new boolean[size][size];
        // 斜线和斜右上的一条斜线
        for (int i = 0; i < size - 1; i++) {
            dp[i][i] = true;  // str[i,i] = true
            dp[i][i + 1] = str[i] == str[i + 1];  // dp表的 base case 右上斜线
        }
        dp[size - 1][size - 1] = true;  // 右下角点
        // 填充右上半个区域
        for (int j = 2; j < size; j++) {
            int left = 0;
            int right = j;
            while (left < size && right < size) {  // 从上到下的斜线
                dp[left][right] = str[left] == str[right] && dp[left + 1][right - 1];
                left++;
                right++;
            }
        }
        return dp;
    }

    public void process(List<List<String>> result, List<String> path, String str, int index, boolean[][] dp) {
        if (index == str.length()) {  // 收集答案
            result.add(new ArrayList<>(path));
        } else {
            for (int end = index; end < str.length(); end++) {
                if (dp[index][end]) {
                    // 标记现场
                    path.add(str.substring(index, end + 1));
                    // 进入下层决策
                    process(result, path, str, end + 1, dp);
                    // 恢复现场
                    path.remove(path.size() - 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        Problem_0131_PalindromePartitioning demo = new Problem_0131_PalindromePartitioning();
        demo.partition("aab");
    }
}
