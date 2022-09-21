package toplikedquestions;

// 最长无重复子串
// https://leetcode.cn/problems/longest-substring-without-repeating-characters/?favorite=2cktkvj
public class Problem_0003_LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }

        char[] charArr = s.toCharArray();
        int[] map = new int[256];
        for (int i = 0; i < 256; i++) {
            map[i] = -1;
        }
        int res = 0;  // 最终的答案
        int pre = -1;  // cur前一个的结果（代替了dp数组）
        int cur = 0;  // 当前的答案
        for (int i = 0; i < charArr.length; i++) {
            pre = Math.max(pre, map[charArr[i]]);  // i-1位置和i位置推不动的最大值
            cur = i - pre;  // cur就是最小值
            res = Math.max(res, cur);
            map[charArr[i]] = i;
        }
        return res;
    }
}
