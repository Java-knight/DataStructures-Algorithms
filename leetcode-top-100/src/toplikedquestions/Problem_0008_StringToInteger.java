package toplikedquestions;

// 字符串转换整数(atoi)
// https://leetcode.cn/problems/string-to-integer-atoi/
public class Problem_0008_StringToInteger {
    public int myAtoi(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        s = removeHeadZero(s.trim());
        if (s == null || s.equals("")) {
            return 0;
        }
        char[] charArr = s.toCharArray();
        if (!isValid(charArr)) {
            return 0;
        }
        // charArr 是符合日常书写的, 正经整数形式
        boolean flag = charArr[0] == '-' ? false : true;
        int minq = Integer.MIN_VALUE / 10;
        int minr = Integer.MIN_VALUE % 10;
        int result = 0;
        int cur = 0;
        for (int i = (charArr[0] == '-' || charArr[0] == '+') ? 1 : 0; i < charArr.length; i++) {
            cur = '0' - charArr[i];
            if ((result < minq) || (result == minq && cur < minr)) {
                return flag ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            result = result * 10 + cur;
        }

        if (flag && result == Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        }
        return flag ? -result : result;
    }


    // 删除字符串前面的零和符号处理
    // case1: '+00673' ——> +673  '00673' ——> 673
    // case2: '- 0005a'——> -
    public String removeHeadZero(String str) {
        // (1) 删除 '0' 字符
        boolean flag = str.startsWith("+") || str.startsWith("-");
        int index = flag ? 1 : 0;
        for (; index < str.length(); index++) {
            if (str.charAt(index) != '0') {
                break;
            }
        }
        // index 到了第一个不是'0'字符的位置
        int end = -1;
        // (2) 左<-右, 判断是否是0~9之间的数
        for (int i = str.length() - 1; i >= index; i--) {
            if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                end = i;
            }
        }
        // (3) 拼接结果: 正负号 + 数字
        return (flag ? String.valueOf(str.charAt(0)) : "") + str.substring(index, end == -1 ? str.length() : end);
    }

    // 过滤器
    public boolean isValid(char[] arr) {
        // base case1 开头不是'+'、'-'、'0'~'9'
        if (arr[0] != '-' && arr[0] != '+' && (arr[0] < '0' || arr[0] > '9')) {
            return false;
        }
        // base case2 arr只有一个'+'、'-', 其它什么都没有
        if ((arr[0] == '-' || arr[0] == '+') && arr.length == 1) {
            return false;
        }
        // base case3 1~len-1 之间判断是否都是0~9之间的数字
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < '0' || arr[i] > '9') {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Problem_0008_StringToInteger dmo = new Problem_0008_StringToInteger();
        dmo.myAtoi("2147483648");
    }
}
