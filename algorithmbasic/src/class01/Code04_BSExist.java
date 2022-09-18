package class01;

import java.util.Arrays;

// 二分查找（寻找一个有序数组上是否存在一个target的数）
// 测试：https://leetcode.cn/problems/binary-search/
public class Code04_BSExist {

    // 时间复杂度 O(logN)，空间复杂度 O(1)
    public static boolean exist(int[] sortedArr, int target) {
        if (sortedArr == null || sortedArr.length == 0) {
            return false;
        }
        int left = 0;
        int right = sortedArr.length - 1;
        int mid = 0;
        // [left, right]
        while (left < right) {
            mid = left + ((right - left) >> 1);
            if (sortedArr[mid] == target) {
                return true;
            } else if (sortedArr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return sortedArr[left] == target;
    }

    // 对数器
    public static boolean comparator(int[] sortedArr, int target) {
        for (int cur : sortedArr) {
            if (cur == target) {
                return true;
            }
        }
        return false;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        // create arr memory
        int[] arr = new int[(int)( (maxSize + 1) * Math.random())]; // 长度随机
        // [-maxValue+2, maxValue]
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }


    public static void main(String[] args) {
        int testTime = 50000;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            Arrays.sort(arr);
            int value = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
            if (exist(arr, value) != comparator(arr, value)) {
                succeed = false;
                break;
            }
        }

        System.out.println(succeed ? "Nick!" : "Fucking fucked!");
    }
}
