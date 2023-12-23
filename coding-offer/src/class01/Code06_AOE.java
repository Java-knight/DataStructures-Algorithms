package class01;

import java.util.Arrays;

/**
 * 给定两个非负数组 x 和 hp，长度都是 N，再给定一个正数 range，x数组有序，x[i] 表示 i 号怪兽在x轴上的位置；
 * hp[i] 表示i号怪兽的血量，hp数组不一定有序，再给定一个正数range，表示如果法师释放技能的范围长度被打到每只怪兽损失1点血量。
 * 返回要把所有怪兽血量清空，至少需要释放多少次AOE技能？
 * @desc
 * @author knight
 * @date 2023/12/23
 */
public class Code06_AOE {

    /**
     * 暴力递归(全排列); 边界没有处理
     * 每一个位置都尝试一次
     */
    public static int minAoe1(int[] x, int[] hp, int range) {
        boolean allClear = true;
        for (int i = 0; i < hp.length; i++) {
            if (hp[i] > 0) {
                allClear = false;
                break;
            }
        }

        if (allClear) {  // 递归结束条件
            return 0;
        } else {
            int ans = Integer.MAX_VALUE;
            for (int left = 0; left < x.length; left++) {
                if (hasHp(x, hp, left, range)) {  // 是否可以掉血
                    // 进入下层递归树: 从 left 开始向右range范围的小怪都掉血
                    minusOneHp(x, hp, left, range);
                    ans = Math.min(ans, minAoe1(x, hp, range) + 1);
                    // 恢复现场: 给掉血的小怪加血回来
                    addOneHp(x, hp, left, range);
                }
            }
            return ans;
        }
    }

    /**
     * 判断从 left...left+range范围上的小怪是否可以掉血; 有一个小怪可以掉血就返回true
     */
    private static boolean hasHp(int[] x, int[] hp, int left, int range) {
        for (int index = left; index < x.length && x[index] - x[left] <= range; index++) {
            if (hp[index] > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 从 left...left+range 范围上的小怪依次掉血, 每个位置上的小怪 hp 就掉一点血
     */
    private static void minusOneHp(int[] x, int[] hp, int left, int range) {
        for (int index = left; index < x.length && x[index] - x[left] <= range; index++) {
            hp[index]--;
        }
    }

    /**
     * 从 left...left+range 范围上的小怪依次掉血, 每个位置上的小怪 hp 就加一点血
     */
    private static void addOneHp(int[] x, int[] hp, int left, int range) {
        for (int index = left; index < x.length && x[index] - x[left] <= range; index++) {
            hp[index]++;
        }
    }

    /**
     * 贪心;
     * (1) 总是用技能的最左边缘打到当前最左侧的 hp 不为 0 的怪物
     * (2) 然后向右找下一个每死的怪物,
     * 重复(1)(2)
     */
    public static int minAoe2(int[] x, int[] hp, int range) {
        int len = x.length;
        // (1) 记录 left...right 的小怪位置
        int[] cover = new int[len];  // cover[left] = right: 表示再i位置上放技能, 最多可以覆盖到 left...right-1 位置上的小怪
        int right = 0;
        for (int left = 0; left < len; left++) {
            while (right < len && x[right] - x[left] <= range) {  // 技能是否可以继续向右覆盖
                right++;
            }
            cover[left] = right;
        }

        // (2) 开始计算打怪, 计算技能次数
        int result = 0;
        for (int cur = 0; cur < len; cur++) {
            if (hp[cur] > 0) {
                int curHp = hp[cur];
                for (int index = cur; index < cover[cur]; index++) {  // cur...cover[cur]-1 位置上的小怪掉血curHp
                    hp[index] -= curHp;
                }
                result += curHp;  // curHp就是释放技能次数
            }
        }
        return result;
    }

    /**
     * 贪心 + 线段树; 时间复杂度 O(NlogN)
     * TODO 线段树目前不了解, 后续了解了写, 后续进行性能测试
     */
    public static int minAoe3(int[] x, int[] hp, int range) {
        return -1;
    }

    // for test(生成随机数组)
    public static int[] randomArray(int len, int valueMax) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * valueMax) + 1;
        }
        return arr;
    }

    // for test(数组深拷贝)
    public static int[] copyArray(int[] arr) {
        int len = arr.length;
        int[] copyArray = new int[len];
        for (int i = 0; i < len; i++) {
            copyArray[i] = arr[i];
        }
        return copyArray;
    }

    // R * N >= X, 否则技能大不到小怪了
    public static void main(String[] args) {
        int N = 5;  // x/hp数组长度最大值
        int X = 50;  // 小怪位置最大值
        int H = 6;  // 小怪血量最大值
        int R = 10;  // AOE技能最大值
        int testTime = 1;
        // 正确性测试
        System.out.println("test start...");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N) + 1;
            int[] x1 = randomArray(len, X);
            Arrays.sort(x1);
            int[] hp1 = randomArray(len, H);
            int[] x2 = copyArray(x1);
            int[] hp2 = copyArray(hp1);
            int range = (int) (Math.random() * R) + 1;
            int ans1 = minAoe1(x1, hp1, range);
            int ans2 = minAoe2(x2, hp2, range);
            if (ans1 != ans2) {
                System.out.println("fail");
            }
        }
        System.out.println("test success");

        // 性能测试
        N = 50000;
        long start, end;
        int[] x1 = randomArray(N, N);
        Arrays.sort(x1);
        int[] hp1 = new int[N];
        for (int i = 0; i < N; i++) {
            hp1[i] = 5 * 10 * i;
        }
        int[] x2 = copyArray(x1);
        int[] hp2 = copyArray(hp1);
        int range = 1000;

        start = System.currentTimeMillis();
        System.out.println(minAoe1(x1, hp1, range));
        end = System.currentTimeMillis();
        System.out.println("[暴力]运行时间: " + (end - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(minAoe2(x2, hp2, range));
        end = System.currentTimeMillis();
        System.out.println("[贪心]运行时间: " + (end - start) + "ms");
    }
}
