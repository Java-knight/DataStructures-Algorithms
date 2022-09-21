package class02;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 题目：一个数组中有一种数出现K次，其它数都出现了M次，M>1，K<M。找到出现了K次的数。
// 要求，额外空间复杂度O(1)，时间复杂度O(N)
public class Code03_KM {

    // 解法1: 最简洁的方法
    public static int km(int[] arr, int k, int m) {
        int[] help = new int[32];
        for (int val : arr) {
            for (int i = 0; i < 32; i++) {
                help[i] += (val >> i) & 1;
            }
        }

        int result = 0;
        for (int i = 0; i < 32; i++) {
            help[i] %= m;
            if (help[i] != 0) {
                result |= 1 << i;
            }
        }
        return result;
    }

    public static Map<Integer, Integer> map = new HashMap<>();

    // 解法2: 使用map去统计arr数组中每个val的二进制位数
    public static int onlyKTimes(int[] arr, int m) {
        if (map.size() == 0) {
            mapCreate(map);
        }

        int[] t = new int[32];
        // t[0] 0 位置的1出现了几个
        // t[i] i 位置的1出现了几个
        for (int val : arr) {
            while (val != 0) {
                int rightOne = val & (-val);
                t[map.get(rightOne)]++;
                val ^= rightOne;
            }
        }
        int result = 0;
        for (int i = 0; i < 32; i++) {
            if (t[i] % m != 0) {
                result |= (1 << i);  // 将 i位置的 1 设置到result上
            }
        }
        return result;
    }

    public static void mapCreate(Map<Integer, Integer> map) {
        int val = 1;
        for (int i = 0; i < 32; i++) {
            map.put(val, i);
            val <<= 1;
        }
    }

    // 对数器, 将arr中的val的二进制位放入map<val, count>做词频统计
    public static int comparator(int[] arr, int k, int m) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int val : arr) {
            if (map.containsKey(val)) {
                map.put(val, map.get(val) + 1);
            } else {
                map.put(val, 1);
            }
        }

        for (int val : map.keySet()) {
            if (map.get(val) == k) {
                return val;
            }
        }
        return -1;
    }

    // for test
    public static int[] randomArray(int maxKinds, int range, int k, int m) {
        int ktimeNum = randomNumber(range);  // 种数
        // 2,
        int numKinds = (int) (Math.random() * maxKinds) + 2;
        int[] arr = new int[k + (numKinds - 1) * m];
        int index = 0;
        for (; index < k; index++) {
            arr[index] = ktimeNum;
        }
        numKinds--;
        Set<Integer> set = new HashSet<>();
        set.add(ktimeNum);
        while (numKinds != 0) {
            int curNum = 0;
            do {
                curNum = randomNumber(range);
            } while (set.contains(curNum));
            set.add(curNum);
            numKinds--;
            for (int i = 0; i < m; i++) {
                arr[index++] = curNum;
            }
        }
        // arr 已经填好了
        // 打乱 arr 中的数
        for (int i = 0; i < arr.length; i++) {
            // i 位置的数，像随机和j位置的数做交换
            int j = (int) (Math.random() * arr.length); // 0 ~ N-1
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
        return arr;
    }

    // [-range, +range]
    public static int randomNumber(int range) {
        return ((int) (Math.random() * range) + 1) - ((int) (Math.random() * range) + 1);
    }

    public static void main(String[] args) {
        int testTimes = 100000;
        int kinds = 100;
        int range = 200;
        int maxValue = 9;
        System.out.println("测试开始: ");
        for (int i = 0; i < testTimes; i++) {
            int a = (int) (Math.random() * maxValue) + 1;  // 1 ~ maxValue
            int b = (int) (Math.random() * maxValue) + 1;  // 1 ~ maxValue
            int k = Math.min(a, b);
            int m = Math.max(a, b);
            // k < m
            if (k == m) {
                m++;
            }
            int[] arr = randomArray(kinds, range, k, m);
            int ans1 = km(arr, k, m);
            int ans2 = comparator(arr, k, m);
            if (ans1 != ans2) {
                System.out.println("出错了！");
            }
        }
        System.out.println("测试结束");
    }
}
