package toplikedquestions;


// 验证回文串
// https://leetcode.cn/problems/valid-palindrome/
public class Problem_0125_ValidPalindrome {

    // 思路: 直接搞左右两个指针, left向右, right向左;
    // if s[left] != s[right] false, 否则left++, right--继续
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        char[] str = s.toCharArray();
        int left = 0;
        int right = str.length - 1;
        while (left < right) {
            if (validChar(str[left]) && validChar(str[right])) {  // 必须是大小写字母或数字
                if (!equal(str[left], str[right])) {  // 判断是否一样(字母/数字)
                    return false;
                }
                left++;
                right--;
            } else {  // 符号/空格
                left += validChar(str[left]) ? 0 : 1;
                right -= validChar(str[right]) ? 0 : 1;
            }
        }
        return true;
    }

    // 判断c是否是大小写字母或数字
    public boolean validChar(char c) {
        return isLetter(c) || isNumber(c);
    }

    // 判断是否是大小写字母
    public boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    // 判断c是否是0~9之间的数字
    public boolean isNumber(char c) {
        return (c >= '0' && c <= '9');
    }

    // 判断c1和c2是否一样(ascii表 A=65, a=97, 两者差32)
    public boolean equal(char c1, char c2) {
        if (isNumber(c1) || isNumber(c2)) {  // 有一个是数字, 就直接比
            return c1 == c2;
        }
        return (c1 == c2) || (Math.max(c1, c2) - Math.min(c1, c2) == 32);  // 设配大小写字母的比较
    }
}
