package class01;

import java.util.Arrays;


// 题目：在一个有序数组中, 找到 >= 某个数最左侧的位置
public class Code05_BSNearLeft {

    // 在 sortedArr 上，找到满足 >= target 的最左边的下标
    public static int nearestIndex(int[] sortedArr, int target) {
        int left = 0;
        int right = sortedArr.length - 1;
        int mid = 0;
        int index = -1;
        while (left <= right) {
            mid = left + ((right - left) >> 1);
            if (sortedArr[mid] >= target) {
                index = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return index;
    }

    // 对数器
    public static int comparator(int[] sortedArr, int target) {
        for (int i = 0; i < sortedArr.length; i++) {
            if (sortedArr[i] >= target) {
                return i;
            }
        }
        return -1;
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

    //for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            Arrays.sort(arr);
            int value = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
            if (nearestIndex(arr, value) != comparator(arr, value)) {
                succeed = false;
                printArray(arr);
                System.out.printf("target: %d\n", value);
                System.out.printf("left nearestIndex result: %d\n", nearestIndex(arr, value));
                System.out.printf("comparator result: %d\n", comparator(arr, value));
                break;
            }
        }
        System.out.println(succeed ? "Nick!" : "Fucking fucked!");
    }
}
