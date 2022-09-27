package toplikedquestions;


// 搜索插入位置
// https://leetcode.cn/problems/search-insert-position/
public class Problem_0035_SearchInsertPosition {
    // 二分搜索
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = left + ((right - left) >> 1);
            if (target > nums[mid]) {
                left = mid + 1;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                break;
            }
        }
        return target <= nums[mid] ? mid : mid + 1;
    }
}
