package toplikedquestions;


// 只出现一次的数字
// https://leetcode.cn/problems/single-number
public class Problem_0136_SingleNumber {


    public int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }
}
