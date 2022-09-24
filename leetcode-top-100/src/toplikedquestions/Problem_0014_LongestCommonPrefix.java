package toplikedquestions;


// 最长公共前缀
// https://leetcode.cn/problems/longest-common-prefix/
public class Problem_0014_LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        char[] charArr = strs[0].toCharArray();  // 以0号位置的字符串为比较对象
        int min = Integer.MAX_VALUE;
        for (String str : strs) {  // 遍历字符串数组
            char[] tmp = str.toCharArray();
            int index = 0;
            while (index < tmp.length && index < charArr.length) {  // 遍历字符串
                if (charArr[index] != tmp[index]) {
                    break;
                }
                index++;
            }
            min = Math.min(min, index);
        }
        return strs[0].substring(0, min);
    }
}
