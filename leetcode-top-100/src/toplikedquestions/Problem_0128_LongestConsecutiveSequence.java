package toplikedquestions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 最长连续序列
// https://leetcode.cn/problems/longest-consecutive-sequence/
public class Problem_0128_LongestConsecutiveSequence {

    // 思路: 先进行排序, 然后用一个pre记录上一个元素的值, cur表示这个区间连续长度
    // base case1 如果arr[i] == pre+1, 当前cur++,
    // base case2 如果arr[i] == pre, 表示重复元素, 直接跳过
    // base case3 出现arr[i] != pre+1, 收集答案, 这个区间的结果
    // result: 返回所有区间的最大值
    // 注意: for loop出来, 需要再次收集答案, 因为可能最后一个区间没有收集答案
    public int longestConsecutive(int[] nums) {
        Arrays.sort(nums);
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int result = 1;
        int cur = 1;
        int pre = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (pre == nums[i]) {  // base case2 重复元素
                continue;
            } else if (pre + 1 == nums[i]) {  // base case1 区间长度++
                cur++;
            } else {  // base case3 收集答案
                result = Math.max(result, cur);
                cur = 1;
            }
            pre = nums[i];
        }
        result = Math.max(result, cur);  // 打补丁, 收集最有一个区间的长度
        return result;
    }

//    // 思路: 使用map维护一个头尾区间, 始终保持每个连续的头尾都存放的是连续的最大值
//    // 注意: 重复元素需要去掉
//    public int longestConsecutive(int[] nums) {
//        int result = 0;
//        Map<Integer, Integer> map = new HashMap<>();
//        for (int num : nums) {
//            if (map.containsKey(num)) {  // 去重
//                continue;
//            }
//            map.put(num, 1);
//            int preVal = map.containsKey(num - 1) ? map.get(num - 1) : 0;  // 获取需要维护的头位置
//            int nextVal = map.containsKey(num + 1) ? map.get(num + 1) : 0; // 获取需要维护的尾位置
//            int val = preVal + nextVal + 1;  // 头尾的值
//            map.put(num - preVal, val);  // 更新头
//            map.put(num + nextVal, val);  // 更新尾
//            result = Math.max(result, val);  // 收集答案
//        }
//        return result;
//    }
}
