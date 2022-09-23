package toplikedquestions;

// 整数反转
// https://leetcode.cn/problems/reverse-integer/
public class Problem_0007_ReverseInteger {

    public int reverse(int x) {
        int result = 0;
        while (x != 0) {
            // 判断是否越界
            if ((result * 10) / 10 != result) {
                return 0;
            }
            result = result * 10 + x % 10;
            x /= 10;
        }
        return result;
    }


    // (1) 基本的操作 res = res * 10 + x % 10; x /= 10
    // (2) 将x转为负数操作, 因为负数的域大于正数
    // (3) 防止出现越界
//    public int reverse(int x) {
//        boolean neg = ((x >>> 31) & 1) == 1;  // 是否是负数
//        x = neg ? x : -x;
//        int result = 0;
//        int m = Integer.MIN_VALUE / 10;
//        int o = Integer.MIN_VALUE % 10;
//        while (x != 0) {
//            if (result < m || (result == m && x % 10 < o)) {
//                return 0;
//            }
//            result = result * 10 + x % 10;
//            x /= 10;
//        }
//        return neg ? result : Math.abs(result);
//    }

}
