package toplikedquestions;


// 移除元素
// https://leetcode.cn/problems/remove-element/
public class Problem_0027_RemoveElement {

    // 双指针(左右指针, 重复元素避免的大量赋值操作)
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            if (nums[left] == val) {
                nums[left] = nums[right];
                right--;
            } else {
                left++;
            }
        }
        return left;
    }

//    // 快慢指针(遇到重复元素需要不断的赋值)
//    public int removeElement(int[] nums, int val) {
//        if (nums == null || nums.length == 0) {
//            return 0;
//        }
//        int slow = 0;
//        for (int fast = 0; fast < nums.length; fast++) {
//            if (nums[fast] != val) {
//                nums[slow++] = nums[fast];
//            }
//        }
//        return slow;
//    }

}
