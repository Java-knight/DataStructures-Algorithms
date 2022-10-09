package toplikedquestions;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 单词拆分
// https://leetcode.cn/problems/word-break/
public class Problem_0139_WordBreak {

    // 方法3最快, 面试从方法1开始聊起
    // 方法2: 动态规划(求可以分解的方法数), 时间复杂度O(N^3)
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        int size = s.length();
        int[] dp = new int[size + 1];
        dp[size] = 1;  // base case
        // dp[i]依赖dp[i+1]的结果
        for (int index = size - 1; index >= 0; index--) {  // dfs
            int ways = 0;
            for (int end = index; end < s.length(); end++) {
                String pre = s.substring(index, end + 1);
                if (dict.contains(pre)) {
                    ways += dp[end + 1];
                }
            }
            dp[index] = ways;
        }
        return dp[0] != 0;
    }

//    // 方法1: dfs暴力递归(求可以分解的方法数)
//    public boolean wordBreak(String s, List<String> wordDict) {
//        Set<String> dict = new HashSet<>(wordDict);
//        return process(s, 0, dict) != 0;
//    }
//
//    public int process(String str, int index, Set<String> dict) {
//        if (index == str.length()) {  // 收集答案
//            return 1;
//        } else {
//            int ways = 0;
//            for (int end = index; end < str.length(); end++) {
//                String pre = str.substring(index, end + 1);
//                if (dict.contains(pre)) {
//                    // 标记现场(不需要)
//                    // 进入下层决策
//                    ways += process(str, end + 1, dict);
//                    // 恢复现场(不需要)
//                }
//            }
//            return ways;
//        }
//    }

//    // 方法3: 前缀树+动态规划(求可以分解的方法数), 时间复杂度O(N^2)
//    public boolean wordBreak(String s, List<String> wordDict) {
//        Node root = new Node();
//        // build trie tree
//        for (String str : wordDict) {
//            char[] word = str.toCharArray();
//            Node cur = root;
//            int index = 0;
//            for (int i = 0; i < word.length; i++) {
//                index = word[i] - 'a';
//                if (cur.nexts[index] == null) {  // 创建子孩子
//                    cur.nexts[index] = new Node();
//                }
//                cur = cur.nexts[index];
//            }
//            cur.end = true;
//        }
//
//        // 动态规划开始
//        char[] str = s.toCharArray();
//        int size = s.length();
//        int[] dp = new int[size + 1];
//        dp[size] = 1;  // base case
//        // dp[index]依赖dp[end+1]的结果
//        for (int index = size - 1; index >= 0; index--) {  // dfs
//            Node cur = root;
//            for (int end = index; end < size; end++) {
//                cur = cur.nexts[str[end] - 'a'];
//                if (cur == null) {  // trie tree该节点不存在(字典中不存在该字符)
//                    break;
//                }
//                if (cur.end) {  // 该节点存在
//                    dp[index] += dp[end + 1];
//                }
//            }
//        }
//        return dp[0] != 0;
//    }
//
//    public class Node {
//        public boolean end;  // end节点是否是结尾节点 , 'abc', a/b节点不是结尾false, c节点是结尾节点true
//        public Node[] nexts;  // end节点的下一层节点
//
//        public Node() {
//            this.end = false;
//            this.nexts = new Node[26];  // 26个字母
//        }
//    }
}
