package toplikedquestions;


// 在排序数组中查找元素的第一个和最后一个位置
// https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/
public class Problem_0034_FindFirstAndLastPositionOfElementInSortedArray {

    // 二分找最右边和最左边, 分两个函数调
    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        if (nums == null || nums.length == 0) {
            return result;
        }
        result[0] = findLeft(nums, target);
        result[1] = findRight(nums, target);
        return result;
    }

    public int findLeft(int[] nums, int target) {
        int left  = 0;
        int right = nums.length - 1;
        int mid = 0;
        int leftIndex = -1;
        while (left <= right) {
            mid = left + ((right - left) >> 1);
            if (target < nums[mid]) {
                right = mid - 1;
            } else if (target > nums[mid]) {
                left = left + 1;
            } else {
                leftIndex = mid;
                right = mid - 1;
            }
        }
        return leftIndex;
    }

    public int findRight(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        int rightIndex = -1;
        while (left <= right) {
            mid = left + ((right - left) >> 1);
            if (target > nums[mid]) {
                left = mid + 1;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                rightIndex = mid;
                left = mid + 1;
            }
        }
        return rightIndex;
    }
}
