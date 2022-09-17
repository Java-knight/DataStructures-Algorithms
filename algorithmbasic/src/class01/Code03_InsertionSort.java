package class01;

// 插入排序
// 验证 https://www.nowcoder.com/practice/2baf799ea0594abd974d37139de27896?tpId=308&tqId=1089529&ru=/exam/oj&qru=/ta/algorithm-start/question-ranking&sourceUrl=%2Fexam%2Foj%3Fpage%3D1%26tab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D308
public class Code03_InsertionSort {

    // 时间复杂度 O(N^2), 空间复杂度 O(1)
    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        // 0 ~ 1   做到有序
        // 0 ~ 2   做到有序
        // 0 ~ 3   做到有序
        // 0 ~ N-1 做到有序
        for (int i = 1; i < arr.length; i++) {   // 0 ~ i 做到有序
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {  // j 的左边有比 j大的，就交换
                swap(arr, j, j + 1);
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void main(String[] args) {

    }
}
