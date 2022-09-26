package toplikedquestions;

// 371 是加法
// 两数相除
// https://leetcode.cn/problems/divide-two-integers/
public class Problem_0029_DivideTwoIntegers {

    // 除法
    // 边界条件: 系统最小表示 Integer.MIN_VALUE
    // (1) dividend 和 divisor 都是系统最小, 结果 1
    // (2) dividend不是系统最小, divisor 是系统最小,结果0. 因为结果范围一定是(-1, 1)
    // (3) dividend是系统最小, divisor 是-1, 结果系统最大, 因为系统最小的绝对值比系统最大放不下;
    //     divisor 不是系统最小也不是-1, 需要使用补偿法, 假设系统最大是-20, divisor是4,
    //     我就先给dividend+1 = -19, res = -19/4 = -4, (dividend-res*divisor)/divisor
    //     需要补偿的值 = (-20-(-4)*4)/4 = -1, 结果 res+补偿值 = -4+(-1) = -5
    // (4) dividend 和 divisor 都不是系统最小, 直接调用div进行计算(div会将dividend和divisor使用它们的绝对值进行计算)
    // 问题: 为啥(3) 不能直接使用(4)呢? 要用什么补偿法, 因为(4) 需要转换为绝对值, (3)中的系统最小值直接就溢出了
    public int divide(int dividend, int divisor) {
        // base case1\2
        if (divisor == Integer.MIN_VALUE) {
            return dividend == Integer.MIN_VALUE ? 1 : 0;
        }
        // base case3
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == negNum(1)) {
                return Integer.MAX_VALUE;
            }
            int result = div(add(dividend, 1), divisor);
            // res + (dividend-res*divisor)/divisor
            return add(result, div(minus(dividend, multi(result, divisor)), divisor));
        }
        // base case4
        return div(dividend, divisor);
    }

    // 除法: 将a和b转化为绝对值, 使用a除b
    // 思路: 16*12 = 16*2 + 16*10 = 16*2^2 + 16*2^3 = 192
    // 将乘法反者来, 192/12 = 192-2^3*12-2^2*12-2^1*12-2^0*12 = 32
    // 192    11000000    1100000    110000    11000    1100=32
    // 12      1100000     110000     11000     1100    1100  (res就是12每次左移的位数)
    // res  =    2^3   +    2^2    +    2^1   +   2^0  (代码中使用的是|符号作为+)
    public int div(int a, int b) {
        int x = isNeg(a) ? negNum(a) : a;
        int y = isNeg(b) ? negNum(b) : b;
        int result = 0;
        // i = 31; i > -1; i--
        for (int i = 31; i > negNum(1); i = minus(i, 1)) {
            if ((x >> i) >= y) {  // y左移i位, 达到最近的小于x; x 11000 y 00001 y就可以左移4位
                result |= (1 << i);
                x = minus(x, y << i);
            }
        }
        return isNeg(a) ^ isNeg(b) ? negNum(result) : result;
    }

    // 是否是负数
    public boolean isNeg(int n) {
        return n < 0;
    }

    // 获取一个数的相反数, 不能用负号
    // 10    1010    1111...0101    1111...0110
    // -10: 二进制是10的二进制取反+1    1111...0110
    public int negNum(int n) {
        return add(~n, 1);
    }

    // 减法
    // a - b == a + (-b)
    public int minus(int a, int b) {
        return add(a, negNum(b));
    }

    // 乘法
    // 15 * 10: 一般是用0*15+10*25
    // 代码实现一样的, 只是把15 和 10转成二进制进行运算
    // 15    1111    11110    111100    1111000
    // 10    1010      101        10          1
    // res =  0    +   30   +    0     +   80    = 150
    public int multi(int a, int b) {
        int res = 0;
        while (b != 0) {
            if ((b & 1) != 0) {
                res = add(res, a);
            }
            a <<= 1;
            b >>>= 1;
        }
        return res;
    }

    public int add(int a, int b) {
        int sum = a;
        while (b != 0) {
            sum = a ^ b;
            b = (a & b) << 1;
            a = sum;
        }
        return sum;
    }
}
