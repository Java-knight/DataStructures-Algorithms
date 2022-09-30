package toplikedquestions;



// x的平方根
// https://leetcode.cn/problems/sqrtx/
public class Problem_0069_SqrtX {
    // x不可能输入负数
    // 二分法, 每次都看mid*mid和x的关系, mid*mid > x, right向左移动; mid*mid <= x, left向右移动
    // 使用long去接变量, 防止溢出
    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        if (x < 4) {
            return 1;
        }
        int result = 1;
        long left = 0;
        long right = x;
        long mid = 0;
        while (left <= right) {
            mid = left + ((right - left) >> 1);
            if (mid * mid <= x) {
                result = (int) mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }
}
