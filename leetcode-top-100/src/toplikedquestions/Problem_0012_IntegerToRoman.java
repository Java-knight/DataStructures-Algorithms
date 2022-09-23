package toplikedquestions;


// 整数转罗马数字
// https://leetcode.cn/problems/integer-to-roman/
public class Problem_0012_IntegerToRoman {

    // 罗马数组规则, 看题干信息
    public String intToRoman(int num) {
        String[][] temp = {
                {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"},
                {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"},
                {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"},
                {"", "M", "MM", "MMM"},
        };
        StringBuilder sb = new StringBuilder();
        sb.append(temp[3][num / 1000 % 10])
                .append(temp[2][num / 100 % 10])
                .append(temp[1][num / 10 % 10])
                .append(temp[0][num % 10]);
        return sb.toString();
    }
}
