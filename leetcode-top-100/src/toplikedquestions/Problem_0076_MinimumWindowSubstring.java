package toplikedquestions;


// 最小覆盖子串
// https://leetcode.cn/problems/minimum-window-substring/
public class Problem_0076_MinimumWindowSubstring {
    // 滑动窗口: 先右滑直到满足要求, 然后左滑到不能在滑, 收集答案; loop 上述操作
    // 花呗思想, 搞一张欠帐表, 先把 t 中的字符放入表中, t就是欠账的, 用s去还款。这里使用map性能没有array好
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        char[] str = s.toCharArray();  // 还款的
        char[] target = t.toCharArray();  // 欠账
        int[] all = new int[256];  // 欠帐表
        for (char c : target) {
            all[c]++;
        }
        int left = 0;
        int right = 0;
        int match = target.length;
        int minLen = -1;  // 最终答案(最小子串)
        int ansLeft = -1;  // 左滑得到的答案
        int ansRight = -1;  // 右滑得到的答案
        while (right != str.length) {  // 右滑
            all[str[right]]--;
            if (all[str[right]] >= 0) {  // 是否是有效还款
                match--;
            }
            if (match == 0) {  // 右滑满足结果了, 外面while判断, 如果还满足, 继续左滑
                while (all[str[left]] < 0) {  // 左滑
                    all[str[left++]]++;
                }
                if (minLen == -1 || minLen > right - left + 1) {  // 收集答案
                    minLen = right - left + 1;
                    ansLeft = left;
                    ansRight = right;
                }
                match++;
                all[str[left++]]++;  // // 有效欠款左滑
            }
            right++;
        }
        return minLen == -1 ? "" : s.substring(ansLeft, ansRight + 1);
    }
}
