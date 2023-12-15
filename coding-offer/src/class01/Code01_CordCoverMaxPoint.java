package class01;

import java.util.Arrays;

/**
 * 题目: 给定一个有序数组 arr，代表坐落在 X 轴上的点，给定一个正数K，
 *      代表绳子的长度，返回绳子最多压中几个点？（即使绳子边缘处覆盖住点也算压中）
 * @author knight
 * @desc
 * @date 2023/12/15
 */
public class Code01_CordCoverMaxPoint {

    /**
     * 贪心+二分; 时间复杂度: O(NlogN)
     * 每次认为当前点就是绳子的末尾点(贪心), 想坐看绳子可以盖住几个点(二分找个数)
     */
    public static int maxPoint1(int[] arr, int K) {
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            int nearest = nearestIndex(arr, i, arr[i] - K);  // 找到的是最左边的下标
            result = Math.max(result, i - nearest + 1);
        }
        return result;
    }


    private static int nearestIndex(int[] arr, int right, int value) {
        int left = 0;
        int index = right;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] >= value) {  // right向左走
                index = mid;
                right = mid - 1;
            } else {  // left向右走
                left = mid + 1;
            }
        }
        return index;
    }

    /**
     * 滑动窗口; 时间复杂度: O(KN) -> O(N); K是常数项
     * 搞两个指针(下标), 一个left, 一个right, arr[right] - arr[left] <= K, 从左滑到右,
     * 每次当right向右滑不动了, 就记录当前window的个数, 只用记录一个全局最大的
     */
    public static int maxPoint2(int[] arr, int K) {
        int left = 0;
        int right = 0;
        int result = 0;
        int len = arr.length;
        while (left < len) {
            while (right < len && arr[right] - arr[left] <= K) {  // 窗口右指针右滑
                right++;
            }
            result = Math.max(result, right - left++);  // 统计结果, 窗口左指针左滑
        }

        return result;
    }

    // for test(对数器, 贪心; 时间复杂度: O(N^2))
    public static int test(int[] arr, int K) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            int pre = i - 1;
            while (pre >= 0 && arr[i] - arr[pre] <= K) {  // 找绳子的最左边
                pre--;
            }
            max = Math.max(max, i - pre);
        }
        return max;
    }

    /**
     * for test(生成随机数组)
     * @param len 数组长度
     * @param max 数组元素中最大的数
     * @return
     */
    public static int[] generateArray(int len, int max) {
        int[] ans = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * max);
        }
        Arrays.sort(ans);
        return ans;
    }

    public static void main(String[] args) {
        int len = 100;
        int max = 1000;
        int testCount = 100000;
        System.out.println("test start...");
        for (int i = 0; i < testCount; i++) {
            int K = (int) (Math.random() * max);
            int[] arr = generateArray(len, max);
            int ans1 = maxPoint1(arr, K);
            int ans2 = maxPoint2(arr, K);
            int ans3 = test(arr, K);
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println("fail");
                break;
            }
        }
        System.out.println("test end");
    }
}
