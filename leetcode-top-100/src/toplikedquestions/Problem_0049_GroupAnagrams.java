package toplikedquestions;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

// 字母异位词分组
// https://leetcode.cn/problems/group-anagrams/
public class Problem_0049_GroupAnagrams {

    // 同情字符串: 两个字符串中字母的数量和种类都相同就称为同情字符串, 比如str1="abc", str2="cba", str1和str2就是同情字符串
    // 思路: 用一个map<String, List<String>>, key放的是经过排序后的str, value放的是同情字符串列表, 使用个list收集map中的values
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] charArr = str.toCharArray();
            Arrays.sort(charArr);  // 排序
            String key = String.valueOf(charArr);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(str);
        }
        for (List<String> val : map.values()) {
            result.add(val);
        }
        return result;
    }
}
