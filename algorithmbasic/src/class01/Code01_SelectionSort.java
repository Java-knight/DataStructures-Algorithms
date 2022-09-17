package class01;

// 选择排序
// 验证 https://www.nowcoder.com/practice/2baf799ea0594abd974d37139de27896?tpId=308&tqId=1089529&ru=/exam/oj&qru=/ta/algorithm-start/question-ranking&sourceUrl=%2Fexam%2Foj%3Fpage%3D1%26tab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D308
public class Code01_SelectionSort {

    // 时间复杂度 O(N^2), 空间复杂度 O(1)
    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        // 0 ~ N-1 找到最小值，放到 0 位置上
        // 1 ~ N-1 找到最小值，放到 1 位置上
        // 2 ~ N-1 找到最小值，放到 2 位置上
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {  // i ~ N-1 上找到最小值的下标
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr, i, minIndex);
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
