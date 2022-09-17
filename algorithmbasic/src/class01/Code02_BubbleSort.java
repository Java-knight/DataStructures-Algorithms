package class01;

// 冒泡排序
// 验证 https://www.nowcoder.com/practice/2baf799ea0594abd974d37139de27896?tpId=308&tqId=1089529&ru=/exam/oj&qru=/ta/algorithm-start/question-ranking&sourceUrl=%2Fexam%2Foj%3Fpage%3D1%26tab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D308
public class Code02_BubbleSort {

    // 时间复杂度 O(N^2), 空间复杂度 O(1)
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        // 0 ~ N-1 谁大谁往右
        // 0 ~ N-2 谁大谁往右
        // 0 ~ N-3 谁大谁往右
        for (int end = arr.length - 1; end > 0; end--) {
            for (int i = 0; i < end; i++) {  // 0 ~ end 将最大的放在end位置上
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {

    }
}
