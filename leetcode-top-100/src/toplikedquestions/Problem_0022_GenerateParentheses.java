package toplikedquestions;

import java.util.ArrayList;
import java.util.List;

// 括号生成
// https://leetcode.cn/problems/generate-parentheses/
public class Problem_0022_GenerateParentheses {

    public List<String> generateParenthesis(int n) {
        char[] path = new char[n << 1];
        List<String> result = new ArrayList<>();
        process(path, 0, 0, n, result);
        return result;
    }

    /**
     * 暴力递归
     * 比如: n = 5, index = 4
     * @param path 当前路径  "()(("
     * @param index 当前路径的位置;
     * @param leftMatch 待匹配的左括号; 1, 当前路径有一个左括号待匹配
     * @param leftRest 剩余可以使用的左括号 2, 当前路径最多还能增加一个左括号
     * @param result 收集路径集合的结果
     */
    public void process(char[] path, int index, int leftMatch, int leftRest, List<String> result) {
        if (index == path.length) {
            result.add(String.valueOf(path));
        } else {
            if (leftRest > 0) {  // 填入左括号
                path[index] = '(';
                process(path, index + 1, leftMatch + 1, leftRest - 1, result);
            }
            if (leftMatch > 0) {  // 填入右括号
                path[index] = ')';
                process(path, index + 1, leftMatch - 1, leftRest, result);
            }
        }
    }


//    // 暴力递归, 没有剪枝的版本
//    public List<String> generateParenthesis(int n) {
//        char[] path = new char[n << 1];
//        List<String> result = new ArrayList<>();
//        process(path, 0, result);
//        return result;
//    }
//
//    // 没有剪纸的代码
//    public void process(char[] path, int index, List<String> result) {
//        if (index == path.length) {
//            if (isValid(path)) {  // 判断是否合法
//                result.add(String.valueOf(path));
//            }
//        } else {
//            path[index] = '(';
//            process(path, index + 1, result);
//            path[index] = ')';
//            process(path, index + 1, result);
//        }
//    }
//
//    public boolean isValid(char[] path) {
//        int count  = 0;
//        for (char c : path) {
//            if (c == '(') {
//                count++;
//            } else {
//                count--;
//            }
//            if (count < 0) {
//                return false;
//            }
//        }
//        return count == 0;
//    }
}
