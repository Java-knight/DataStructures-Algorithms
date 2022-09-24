package toplikedquestions;

// 罗马数字转整数
// https://leetcode.cn/problems/roman-to-integer/
public class Problem_0013_RomanToInteger {

    // 当前index+1位置的数大于index位置的数，就sum-temp[index]; 如果小于, 就 sum+temp[index]
    // result = sum + temp[temp.length - 1]
    public int romanToInt(String s) {
        int[] temp = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case 'M':
                    temp[i] = 1000;
                    break;
                case 'D':
                    temp[i] = 500;
                    break;
                case 'C':
                    temp[i] = 100;
                    break;
                case 'L':
                    temp[i] = 50;
                    break;
                case 'X':
                    temp[i] = 10;
                    break;
                case 'V':
                    temp[i] = 5;
                    break;
                case 'I':
                    temp[i] = 1;
                    break;
            }
        }
        int sum = 0;
        for (int i = 0; i < temp.length - 1; i++) {
            if (temp[i] < temp[i + 1]) {
                sum -= temp[i];
            } else {
                sum += temp[i];
            }
        }
        return sum + temp[temp.length - 1];
    }
}
