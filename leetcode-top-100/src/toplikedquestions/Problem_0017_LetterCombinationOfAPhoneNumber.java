package toplikedquestions;

import java.util.ArrayList;
import java.util.List;

// 电话号码的字母组合
// https://leetcode.cn/problems/letter-combinations-of-a-phone-number/
public class Problem_0017_LetterCombinationOfAPhoneNumber {
    public static char[][] phone = {
            {'a', 'b', 'c'},       // 2  0(按键, 下标)
            {'d', 'e', 'f'},       // 3  1
            {'g', 'h', 'i'},       // 4  2
            {'j', 'k', 'l'},       // 5  3
            {'m', 'n', 'o'},       // 6  4
            {'p', 'q', 'r', 's'},  // 7  5
            {'t', 'u', 'v'},       // 8  6
            {'w', 'x', 'y', 'z'},  // 9  7
    };

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }
        char[] charArr = digits.toCharArray();
        char[] path = new char[charArr.length];
        process(charArr, 0, path, result);
        return result;
    }

    /**
     * 暴力递归
     * @param charArr 按键信息
     * @param index 当前按键的下标位置
     * @param path  当前路径已经收集的信息
     * @param result 收集答案, 将 path 放入
     */
    public void process(char[] charArr, int index, char[] path, List<String> result) {
        if (index == charArr.length) {  // 当前路径结束, 收集答案
            result.add(String.valueOf(path));
        } else {
            char[] cands = phone[charArr[index] - '2'];  // 当前按键的字母
            for (char cur : cands) {
                // 记录当前信息
                path[index] = cur;
                // 进入下一层决策
                process(charArr, index + 1, path, result);
                // 不需要恢复现场(因为是暴力递归, 所有的结果都需要收集)
            }
        }
    }
}
