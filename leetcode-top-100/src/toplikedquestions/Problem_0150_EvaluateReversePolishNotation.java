package toplikedquestions;


import java.util.Deque;
import java.util.LinkedList;

// 逆波兰表达式求值
// https://leetcode.cn/problems/evaluate-reverse-polish-notation/
public class Problem_0150_EvaluateReversePolishNotation {

    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new LinkedList<>();
        for (String str : tokens) {
            if (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")) {  // 出栈进行计算
                compute(stack, str);
            } else {  // 数据, 直接入栈
                stack.push(Integer.valueOf(str));
            }
        }
        return stack.peek();  // 栈中只剩下最后一个元素就是结果
    }

    /**
     * 遇到符号弹出两个数据, 进行运算, 将其结果入栈(num2和num1的顺序不能错)
     * @param stack
     * @param operate 运算符
     */
    public void compute(Deque<Integer> stack, String operate) {
        int num2 = stack.pop();
        int num1 = stack.pop();
        int ans = 0;
        switch (operate) {
            case "+":
                ans = num1 + num2;
                break;
            case "-":
                ans = num1 - num2;
                break;
            case "*":
                ans = num1 * num2;
                break;
            case "/":
                ans = num1 / num2;
                break;
        }
        stack.push(ans);
    }
}
