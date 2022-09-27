package toplikedquestions;


// 通配符匹配
// https://leetcode.cn/problems/wildcard-matching/
public class Problem_0044_WildcardMatching {

    // s由26个小写字母组成, p由小写字母和通配符组成(?, *)

    // 动态规划(dp二维表)(斜率优化)
    public boolean isMatch(String s, String p) {
        char[] sArr = s.toCharArray();
        char[] pArr = p.toCharArray();
        int sLen = sArr.length;
        int pLen = pArr.length;
        boolean[][] dp = new boolean[sLen + 1][pLen + 1];  // 递归终止条件也是index走到arr.length位置, 所以需要创建dp数组是size+1
        // base case1 sArr匹配完了
        dp[sLen][pLen] = true;  // (1) sArr和pArr都匹配完了
        for (int pIndex = pLen - 1; pIndex >= 0; pIndex--) {  // (2) pIndex位置的值依赖于自己(自己属于*)和pIndex+1位置的值([pIndex+1...]能否完全匹配)
            dp[sLen][pIndex] =  pArr[pIndex] == '*' && dp[sLen][pIndex + 1];
        }
        // base case2 pArr已经匹配完了(多余的, 为了方便理解)

        // base case3 sArr从sIndex出发... pArr从pIndex出发...
        // dp[sIndex][pIndex]依赖dp[sIndex+1][pIndex+1]和dp[sIndex+len][pIndex+1]的值
        // 从左到右, 内层从下到上
        for (int sIndex = sLen - 1; sIndex >= 0; sIndex--) {
            for (int pIndex = pLen - 1; pIndex >= 0; pIndex--) {
                // (1) 小写字母
                if (pArr[pIndex] != '?' && pArr[pIndex] != '*') {
                    dp[sIndex][pIndex] = sArr[sIndex] == pArr[pIndex] && dp[sIndex + 1][pIndex + 1];
                    continue;
                }
                // (2) '?'
                if (pArr[pIndex] == '?') {
                    dp[sIndex][pIndex] = dp[sIndex + 1][pIndex + 1];
                    continue;
                }
                // (3) '*'
                // 因为dp[sIndex][pIndex] = dp[sIndex][pIndex + 1] || dp[sIndex+1][pIndex + 1] || dp[sIndex+2][pIndex + 1]...
                // 因为dp[sIndex+1][pIndex] =  dp[sIndex+1][pIndex + 1] || dp[sIndex+2][pIndex + 1]...
                // 所以 dp[sIndex][pIndex] = dp[sIndex][pIndex + 1] || dp[sIndex+1][pIndex]
                dp[sIndex][pIndex] = dp[sIndex + 1][pIndex] || dp[sIndex][pIndex + 1];
            }
        }
        return dp[0][0];
    }

//    // 方法1: 暴力递归
//    public boolean isMatch(String s, String p) {
//        char[] sArr = s.toCharArray();
//        char[] pArr = p.toCharArray();
//        return process(sArr, pArr, 0, 0);
//    }
//
//    // sArr[sIndex...] 能否被 pArr[pIndex...]匹配出来
//    public boolean process(char[] sArr, char[] pArr, int sIndex, int pIndex) {
//        // base case1 sArr已经匹配完了
//        if (sIndex == sArr.length) {  // sIndex ——> ""
//            if (pIndex == pArr.length) {  // pIndex ——> ""
//                return true;
//            } else {  // pIndex ——> "..."
//                // pArr[pIndex] == '*' && pArr[pIndex+1....] ——> 也是空串(从pIndex+1, 后面也要是空串)
//                return pArr[pIndex] == '*' && process(sArr, pArr, sIndex, pIndex + 1);
//            }
//        }
//
//        // base case2 pArr已经匹配完了(多余的, 为了方便理解)
//        if (pIndex == pArr.length) {
//            return sIndex == sArr.length;  // 这个就是false, 因为如果是true的话, 就走base case1分支了
//        }
//
//        // base case3 sArr从sIndex出发... pArr从pIndex出发...
//        // sArr[sIndex] ——> 小写字母
//        // pArr[pIndex] ——> 小写字母\?\*
//        if (pArr[pIndex] != '?' && pArr[pIndex] != '*') {  // (1) 小写字母
//            return sArr[sIndex] == pArr[pIndex] && process(sArr, pArr, sIndex + 1, pIndex + 1);
//        }
//        if (pArr[pIndex] == '?') {  // (2) ?
//            return process(sArr, pArr, sIndex + 1, pIndex + 1);
//        }
//        // pArr[pIndex] == '*'  len=0,pArr[pIndex]一个也没有匹配到字符串, 需要让pArr[pIndex+1...]去匹配sArr[sIndex...]
//        // len=1,pArr[pIndex]匹配到一个字符串, 需要让pArr[pIndex+1...]去匹配sArr[sIndex+1...]
//        // len=2,pArr[pIndex]匹配到两个字符串, 需要让pArr[pIndex+1...]去匹配sArr[sIndex+2...]
//        for (int len = 0; len <= sArr.length - sIndex; len++) {  // (3) *
//            if (process(sArr, pArr, sIndex + len, pIndex + 1)) {
//                return true;
//            }
//        }
//        return false;
//    }

//    // 方法2: 动态规划(dp二维表)
//    public boolean isMatch(String s, String p) {
//        char[] sArr = s.toCharArray();
//        char[] pArr = p.toCharArray();
//        int sLen = sArr.length;
//        int pLen = pArr.length;
//        boolean[][] dp = new boolean[sLen + 1][pLen + 1];  // 递归终止条件也是index走到arr.length位置, 所以需要创建dp数组是size+1
//        // base case1 sArr匹配完了
//        dp[sLen][pLen] = true;  // (1) sArr和pArr都匹配完了
//        for (int pIndex = pLen - 1; pIndex >= 0; pIndex--) {  // (2) pIndex位置的值依赖于自己(自己属于*)和pIndex+1位置的值([pIndex+1...]能否完全匹配)
//            dp[sLen][pIndex] =  pArr[pIndex] == '*' && dp[sLen][pIndex + 1];
//        }
//        // base case2 pArr已经匹配完了(多余的, 为了方便理解)
//
//        // base case3 sArr从sIndex出发... pArr从pIndex出发...
//        // dp[sIndex][pIndex]依赖dp[sIndex+1][pIndex+1]和dp[sIndex+len][pIndex+1]的值
//        // 从左到右, 内层从下到上
//        for (int sIndex = sLen - 1; sIndex >= 0; sIndex--) {
//            for (int pIndex = pLen - 1; pIndex >= 0; pIndex--) {
//                // (1) 小写字母
//                if (pArr[pIndex] != '?' && pArr[pIndex] != '*') {
//                    dp[sIndex][pIndex] = sArr[sIndex] == pArr[pIndex] && dp[sIndex + 1][pIndex + 1];
//                    continue;
//                }
//                // (2) '?'
//                if (pArr[pIndex] == '?') {
//                    dp[sIndex][pIndex] = dp[sIndex + 1][pIndex + 1];
//                    continue;
//                }
//                // (3) '*'
//                for (int len = 0; len <= sLen - sIndex; len++) {
//                    if (dp[sIndex + len][pIndex + 1]) {
//                        dp[sIndex][pIndex] = true;
//                        break;
//                    }
//                }
//            }
//        }
//
//        return dp[0][0];
//    }
}
