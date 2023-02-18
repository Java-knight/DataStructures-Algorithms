package toplikedquestions;


import java.util.Deque;
import java.util.LinkedList;

// 最小栈
// https://leetcode.cn/problems/min-stack/
public class Problem_0155_MinStack {

    // 双栈实现(minStack压入最小数)
    private Deque<Integer> data;
    private Deque<Integer> min;

    // 提交的时候将其改为 MinStack
    public Problem_0155_MinStack() {
        this.data = new LinkedList<>();
        this.min = new LinkedList<>();
    }

    public void push(int val) {
        data.push(val);
        if (min.isEmpty()) {
            min.push(val);
        } else {
            min.push(Math.min(min.peek(), val));
        }
    }

    public void pop() {
        data.pop();
        min.pop();
    }

    public int top() {
        return data.peek();
    }

    public int getMin() {
        return min.peek();
    }
}
