package toplikedquestions;


// 解码方法
// https://leetcode.cn/problems/decode-ways/
public class Problem_0091_DecodeWays {

    // 动态规划
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();

        int size = s.length();
        // dp[index]: 就str[index....] 能转换多少有效, 返回方法数
        int[] dp = new int[size + 1];
        dp[size] = 1;

        // dp[i] 依赖dp[i + 1]和dp[i + 2]位置(从右向左的尝试模型)
        for (int i = size - 1; i >= 0; i--) {
            if (str[i] != '0') {
                // base case1 i位置是1~9
                dp[i] = dp[i + 1];
                if (i + 1 == size) {
                    continue;
                }
                // base case2 i,i+1位置(小于26) "17"
                int num = (str[i] - '0') * 10 + str[i + 1] - '0';
                if (num <= 26) {
                    dp[i] += dp[i + 2];
                }
            }
        }
        return dp[0];
    }


//    // 暴力递归(尝试模型)
//    public int numDecodings(String s) {
//        if (s == null || s.length() == 0) {
//            return 0;
//        }
//        char[] str = s.toCharArray();
//
//        return process(str, 0);
//    }
//
//    // str[index....] 能转换多少有效, 返回方法数
//    public int process(char[] str, int index) {
//        if (index == str.length) {
//            return 1;
//        }
//        // index == 0, 是不能转的; index(1~9)
//        if (str[index] == '0') {
//            return 0;
//        }
//        // base case1 index(1~9)
//        int ways = process(str, index + 1);
//        if (index + 1 == str.length) {
//            return ways;
//        }
//        // base case2 (index index+1)是一个整体
//        // (index index+1) "23" —> 23  "17" —> 17
//        int num = (str[index] - '0') * 10 + str[index + 1] - '0';
//        // num > 26
//        if (num <= 26) {
//            ways += process(str, index + 2);
//        }
//        return ways;
//    }
}
