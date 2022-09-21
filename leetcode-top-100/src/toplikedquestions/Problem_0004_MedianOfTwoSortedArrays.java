package toplikedquestions;

// 寻找两个正序数组的中位数
// 出现问题: golang没有实现
// https://leetcode.cn/problems/median-of-two-sorted-arrays/
public class Problem_0004_MedianOfTwoSortedArrays {


    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int size = nums1.length + nums2.length;
        boolean even = (size & 1) == 0;  // 是否是偶数
        // base case1 两个数组都不为空
        if (nums1.length != 0 && nums2.length != 0) {
            if (even) {  // 偶数长度
                return (double) (findKthNum(nums1, nums2, size >> 1) + findKthNum(nums1, nums2, (size >> 1) + 1)) / 2D;
            } else {  // 奇数长度
                return findKthNum(nums1, nums2, (size >> 1) + 1);
            }
        } else if(nums1.length != 0) { // base case2 数组1不为空, 数组2为空
            if (even) {
                return (double) (nums1[(size - 1) >> 1] + nums1[size >> 1]) / 2;
            } else {
                return nums1[size >> 1];
            }
        } else if(nums2.length != 0) {  // base case3 数组2不为空, 数组1为空
            if (even) {
                return (double) (nums2[(size - 1) >> 1] + nums2[size >> 1]) / 2;
            } else {
                return nums2[size >> 1];
            }
        } else {  // base case4 如果都为空, 直接返回0
            return 0;
        }
    }

    /**
     * 数组arr1和数组arr2不等长, 获取第k小的数
     * @param arr1
     * @param arr2
     * @param kth
     * @return
     */
    public static int findKthNum(int[] arr1, int[] arr2, int kth) {
        int[] longs = arr1.length >= arr2.length ? arr1 : arr2;
        int[] shorts = arr1.length < arr2.length ? arr1 : arr2;
        int l = longs.length;
        int s = shorts.length;
        // base case1 k <= 最小的arr长度
        if (kth <= s) {
            return getUpMedian(shorts, 0, kth - 1, longs, 0, kth - 1);
        }
        // base case2 k > 最大的arr长度
        if (kth > l) {
            //
            if (shorts[kth - l - 1] >= longs[l - 1]) {
                return shorts[kth - l - 1];
            }
            if (longs[kth - s - 1] >= shorts[s - 1]) {
                return longs[kth - s - 1];
            }
            return getUpMedian(shorts, kth - l, s - 1, longs, kth - s, l - 1);
        }
        // base case3 最小arr长度 < k <= 最大arr长度
        if (longs[kth - s - 1] >= shorts[s - 1]) {
            return longs[kth - s - 1];
        }
        return getUpMedian(shorts, 0, s - 1, longs, kth - s, kth - 1);
    }

    /**
     * 两个等长数组获取中位数, end1-start1 == end2-start2
     * 时间复杂度: O(logN)  N = end-start
     * @param arr1 数组arr1
     * @param s1 数组arr1的 start1
     * @param e1 数组arr1的 end1
     * @param arr2 数组arr2
     * @param s2 数组arr2的 start2
     * @param e2 数组arr2的 end2
     * @return
     */
//    public static int getUpMedian(int[] arr1, int s1, int e1, int[] arr2, int s2, int e2) {
//        int mid1 = 0;
//        int mid2 = 0;
//        while (s1 < e1) {
//            mid1 = (s1 + e1) >> 1;
//            mid2 = (s2 + e2) >> 1;
//            // base case1 直接相等
//            if (arr1[mid1] == arr2[mid2]) {
//                return arr1[mid1];
//            }
//            // base case2 奇数长度
//            if (((e1 - s1 + 1) & 1) == 1) {
//                if (arr1[mid1] > arr2[mid1]) {
//                    if (arr2[mid2] >= arr1[mid1 - 1]) {
//                        return arr2[mid2];
//                    }
//                    e1 = mid1 - 1;
//                    s2 = mid2 + 1;
//                } else {  // arr1[mid1] < arr2[mid1]
//                    if (arr1[mid1] >= arr2[mid2 - 1]) {
//                        return arr1[mid1];
//                    }
//                    e2 = mid2 - 1;
//                    s1 = mid1 + 1;
//                }
//            } else {  // base case3 偶数长度
//                if (arr1[mid1] > arr2[mid2]) {
//                    e1 = mid1;
//                    s2 = mid2 + 1;
//                } else {
//                    e2 = mid2;
//                    s1 = mid1 + 1;
//                }
//            }
//        }
//        return Math.min(arr1[s1], arr2[s2]);
//    }

    public static int getUpMedian(int[] arr1, int s1, int e1, int[] arr2, int s2, int e2) {
        int mid1 = 0;
        int mid2 = 0;
        int offset = 0;
        while (s1 < e1) {
            mid1 = (s1 + e1) / 2;
            mid2 = (s2 + e2) / 2;
            offset = ((e1 - s1 + 1) & 1) ^ 1;
            if (arr1[mid1] > arr2[mid2]) {
                e1 = mid1;
                s2 = mid2 + offset;
            } else if (arr1[mid1] < arr2[mid2]) {
                s1 = mid1 + offset;
                e2 = mid2;
            } else {
                return arr1[mid1];
            }
        }
        return Math.min(arr1[s1], arr2[s2]);
    }

    public static void main(String[] args) {

    }
}
