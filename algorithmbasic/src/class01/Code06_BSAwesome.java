package class01;

/**
 * 局部最小值(拓展)
 * 问题: 在一个无序数组中, 相邻元素不相等, 找到任何一个局部最小值
 *  case1: 0 和 1号下标, 如果arr[0] 小于 arr[1], 则arr[0] 局部最小值, 返回0
 *  case2: N-1 和 N号下标, 如果arr[N-1] 小于 arr[N], 则arr[N-1] 局部最小值, 返回N-1
 *  case3: i-1、i、i+1三个元素, 如果arr[i]大于 arr[i-1]和arr[i+1], 则arr[i] 局部最小值, 返回i
 *
 *  case: 4, 2, 3, 6, 5, 8, 4, 7
 *  result: 1
 */
public class Code06_BSAwesome {

    // 思想：通过左右有序来使用二分查找，使用局部的有序也可以进行二分，让 right和left 每次出于有序状态
    public static int getLessIndex(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;  // no exist
        }
        // case1
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        // case2
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }
        // case3
        int left = 1;
        int right = arr.length - 2;
        int mid = 0;
        while (left < right) {
            mid = left + ((right - left) >> 1);
            if (arr[mid] > arr[mid - 1]) {
                right = mid - 1;
            } else if (arr[mid] > arr[mid + 1]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] arr = {4, 2, 3, 6, 5, 8, 4, 7};
        System.out.println(getLessIndex(arr));
    }
}
