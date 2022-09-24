package toplikedquestions;

import java.util.Stack;

// 有效括号
// https://leetcode.cn/problems/valid-parentheses/
public class Problem_0020_ValidParentheses {

    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '{' || c == '(' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char last = stack.pop();
                if ((c == '}' && last != '{') || (c == ']' && last != '[') || (c == ')' && last != '(')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
