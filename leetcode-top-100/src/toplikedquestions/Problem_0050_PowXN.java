package toplikedquestions;


// Pow(x, n)
// https://leetcode.cn/problems/powx-n/
public class Problem_0050_PowXN {
    // n是正数: x^n ——> n = 二进制, 比如x^75 = x^64 *x^8 *x^2 *x^1
    // n是负数: x^n —> -n = 二进制, 结果 1/result
    // 注意: Integer.MIN_VALUE系统最小是无法转为正数的(可以通过+1 就能转为正数)
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1D;
        }
        // 当n = Integer.MIN_VALUE, 让它+1 就可以转为正数了, 后面结果*x就ok了
        int pow = Math.abs(n == Integer.MIN_VALUE ? n + 1 : n);
        double cur = x;  // 2^1、2^2、2^3、2^4...
        double result = 1D;
        while (pow != 0) {
            if ((pow & 1) != 0) {  // pow的二进制当前位置是1, 收集答案
                result *= cur;
            }
            pow >>= 1;
            cur = cur * cur;
        }
        if (n == Integer.MIN_VALUE) {
            result *= x;
        }
        return n < 0 ? (1D / result) : result;
    }

    // 错误方法, leetcode检查不出来, 当x = 1.00000001D 答案是0.0, 是有问题的
//    public double myPow(double x, int n) {
//        if (n == 0) {
//            return 1D;
//        }
//        if (n == Integer.MIN_VALUE) {  // num^min ——> 1/num^max = 0
//            return (x == 1D || x == -1D) ? 1D : 0;
//        }
//        int pow = Math.abs(n);
//        double cur = x;  // 2^1、2^2、2^3、2^4...
//        double result = 1D;
//        while (pow != 0) {
//            if ((pow & 1) != 0) {  // pow的二进制当前位置是1, 收集答案
//                result *= cur;
//            }
//            pow >>= 1;
//            cur = cur * cur;
//        }
//        return n < 0 ? (1D / result) : result;
//    }

}
