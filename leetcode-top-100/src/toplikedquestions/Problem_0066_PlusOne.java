package toplikedquestions;


// 加一
// https://leetcode.cn/problems/plus-one/
public class Problem_0066_PlusOne {

    public int[] plusOne(int[] digits) {
        int size = digits.length;
        for (int i = size - 1; i >= 0; i--) {  // base case1 不是全9的
            if (digits[i] < 9) {  // 小于0, 直接+1返回结果
                digits[i]++;
                return digits;
            }
            digits[i] = 0;  // 当前位置是9 +1后, 将设位置改为0
        }
        // base case2 如果没有再 for 循环中return, 说明digits全是99...9
        int[] result = new int[size + 1];
        result[0] = 1;
        return result;
    }
}
