package toplikedquestions;

// 不同路径
// https://leetcode.cn/problems/unique-paths/
public class Problem_0062_UniquePaths {

    // question: 从左上角走到右下角, 有多少种不同的路径?
    // 思路: 每次只能向下或者向右, 那么不就是排列组合嘛, 直接C(l+r, l/r)
    //      C(N, M) = N!/(M!*(N-M)!) = C(N, (N-M))
    // 注意: 怎么计算阶乘尽量不越界, 相乘前进行约分(需要求最大公约数, 用long放阶乘的结果)
    public int uniquePaths(int m, int n) {
        // C(all, part)
        int part = n - 1;
        int all = n + m - 2;
        long o1 = 1;
        long o2 = 1;
        // C(all, part) = all!/(part!*(all-part)!) = ((part+1)*(part+2)*...all)/(1*2*...(all-part))
        for (int i = part + 1, j = 1; i <= all || j <= all - part; i++, j++) {
            o1 *= i;
            o2 *= j;
            long gcd = gcd(o1, o2);
            o1 /= gcd;
            o2 /= gcd;
        }
        return (int) o1;  // 最终o2肯定是被约分完了
    }

    // 计算最大公约数(直接背过, 手推就行)
    public long gcd(long m, long n) {
        return n == 0 ? m : gcd(n, m % n);
    }

}
