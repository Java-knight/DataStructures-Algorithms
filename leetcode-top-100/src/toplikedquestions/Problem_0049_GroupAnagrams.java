package toplikedquestions;


import java.util.*;

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

//    // 没有使用排序, 用了一个int[26]数组(下标表示字母, num表示字符出现的次数)记录一个str中出现的的次数, 自己通过StringBuild 构造一个区分同情字符串的通用key去判断
//    public List<List<String>> groupAnagrams(String[] strs) {
//        List<List<String>> result = new ArrayList<>();
//        Map<String, List<String>> map = new HashMap<>();
//        for (String str : strs) {
//            int[] record = new int[26];  // 统计字符个数
//            for (char c : str.toCharArray()) {
//                record[c - 'a']++;
//            }
//            StringBuilder sb = new StringBuilder();
//            for (int value : record) {
//                sb.append(String.valueOf(value)).append("_");
//            }
//            String key = sb.toString(); // dac = "1_0_1_1_0_..."(代表同情字符串, 自己构造了一个判断同情字符串的key)
//            if (!map.containsKey(key)) {
//                map.put(key, new ArrayList<>());
//            }
//            map.get(key).add(str);
//        }
//
//        // 收集答案
//        for (List<String> cur : map.values()) {
//            result.add(cur);
//        }
//        return result;
//    }
}
