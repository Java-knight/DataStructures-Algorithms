package class02;


// 题目：如何不用额外变量交换两个数
public class Code01_Swap {

    public static void main(String[] args) {
        int a = 16;
        int b = 603;

        System.out.println("交换前: ");
        System.out.println(a);
        System.out.println(b);


        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("交换后: ");
        System.out.println(a);
        System.out.println(b);

        int[] arr = {3, 1, 100};
        int i = 0;
        int j = 0;

        arr[i] = arr[i] ^ arr[j];  // 0
        arr[j] = arr[i] ^ arr[j];  // 0
        arr[i] = arr[i] ^ arr[j];  // 0

        System.out.println(arr[i] + ", " + arr[j]);
    }
}
