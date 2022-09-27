package toplikedquestions;


// 外观数列
// https://leetcode.cn/problems/count-and-say/
public class Problem_0038_CountAndSay {

    // 递归, 每次获取n-1的值进行读取
    public String countAndSay(int n) {
        if (n < 1) {  // 边界条件
            return "";
        }
        if (n == 1) {  // 递归结束条件
            return "1";
        }
        char[] last = countAndSay(n - 1).toCharArray();
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (int i = 1; i < last.length; i++) {
            if (last[i - 1] == last[i]) {  // 前一个字母和当前字母相同
                count++;
            } else {
                sb.append(String.valueOf(count));
                sb.append(String.valueOf(last[i - 1]));
                count = 1;
            }
        }
        sb.append(String.valueOf(count));
        sb.append(String.valueOf(last[last.length - 1]));
        return sb.toString();
    }
}
