package toplikedquestions;


// 合并两个有序数组
// https://leetcode.cn/problems/merge-sorted-array/
public class Problem_0088_MergeSortedArray {

    // 思路: 两个数组倒叙遍历
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index = nums1.length;
        while (m > 0 && n > 0) {
            if (nums1[m - 1] < nums2[n - 1]) {
                nums1[--index] = nums2[--n];
            } else {
                nums1[--index] = nums1[--m];
            }
        }
        while (m > 0) {
            nums1[--index] = nums1[--m];
        }
        while (n > 0) {
            nums1[--index] = nums2[--n];
        }
    }

    public static void main(String[] args) {
        Problem_0088_MergeSortedArray demo = new Problem_0088_MergeSortedArray();
        int[] arr1 = {1,2,3,0,0,0};
        int[] arr2 = {2,5,6};
        demo.merge(arr1, 3, arr2, 3);
    }
}
