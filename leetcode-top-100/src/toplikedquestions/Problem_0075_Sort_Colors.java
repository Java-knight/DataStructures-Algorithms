package toplikedquestions;



// 颜色分类
// https://leetcode.cn/problems/sort-colors/
public class Problem_0075_Sort_Colors {

    // 荷兰国旗问题(让arr按num划分成小于区、等于区、大于区)
    // 思路: 搞左右两个指针(都是边界指针), 比如left=-1, right=arr.length
    // left推着index向右跑, right向左跑来
    public void sortColors(int[] nums) {
        int left = -1;
        int right = nums.length;
        int index = 0;
        while (index < right) {
            if (nums[index] == 1) {  // 等于区
                index++;
            } else if (nums[index] == 0) {  // 小于区
                swap(nums, index++, ++left);
            } else {  // 大于区
                swap(nums, index, --right);
            }
        }
    }


    public void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
