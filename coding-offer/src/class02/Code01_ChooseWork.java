package class02;

import java.util.*;

/**
 * 给定数组 hard 和 money，长度都为 N
 * hard[i] 表示 i 号工作的难度，money[i] 表示 i 号工作的收入
 * 给定数组 ability，长度都为 M，ability[j] 表示 j 号人的能力
 * 每一号工作，都可以提供无数的岗位，难度和收入都一样
 * 但是人的能力必须 >= 这份工作的难度，才能上班
 * 返回一个长度为 M 的数组 ans，ans[j] 表示 j 号人能获得的最好收入（赚钱最多）
 * @desc
 * @author knight
 * @date 2023/12/23
 */
public class Code01_ChooseWork {

    /**
     * 贪心 + 二分查找; 时间复杂度 O(NlogN)[有排序]
     */
    public static int[] getMoneys1(int[] hard, int[] money, int[] ability) {
        // (1) 构建一个job, 方便操作
        int len = hard.length;
        Job[] job = new Job[len];
        for (int i = 0; i < len; i++) {
            job[i] = new Job(money[i], hard[i]);
        }

        // (2) job进行排序: 根据工作难度从小达到, 难度相同, 根据收入从大到小
        Arrays.sort(job, new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                return o1.hard != o2.hard ? (o1.hard - o2.hard) : (o2.money - o1.money);
            }
        });

        // (3) 删除无效工作: 当工作难度相同时, 收入低; 当工作难度升高, 收入低(或者相等)
        List<Job> jobList = new ArrayList<>();
        jobList.add(job[0]);
        Job pre = job[0];
        for (int i = 1; i < len; i++) {
            if (job[i].hard != pre.hard && job[i].money > pre.money) {
                jobList.add(job[i]);
                pre = job[i];
            }
        }
        Job[] sortJob = jobList.toArray(new Job[0]);

        // (4) 给对应ability的人分配工作
        int[] ans = new int[ability.length];
        for (int i = 0; i < ability.length; i++) {
            ans[i] = nearestIndex(sortJob, ability[i]);
        }

        return ans;
    }

    /**
     * 二分查找变种, sortJob[index].hard <= target 最右边的数
     */
    private static int nearestIndex(Job[] sortJob, int target) {
        int left = 0;
        int right = sortJob.length - 1;
        int index = -1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (sortJob[mid].hard <= target) {
                index = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return index != -1 ? sortJob[index].money : 0;
    }

    /**
     * 贪心 + 有序表; 时间复杂度 O(NlogN)[有排序]
     */
    public static int[] getMoneys2(int[] hard, int[] money, int[] ability) {
        // (1) 构建一个job, 方便操作
        int len = hard.length;
        Job[] job = new Job[len];
        for (int i = 0; i < len; i++) {
            job[i] = new Job(money[i], hard[i]);
        }

        // (2) job进行排序: 根据工作难度从小达到, 难度相同, 根据收入从大到小
        Arrays.sort(job, new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                return o1.hard != o2.hard ? (o1.hard - o2.hard) : (o2.money - o1.money);
            }
        });

        // (3) 删除无效工作: 当工作难度相同时, 收入低; 当工作难度升高, 收入低(或者相等)
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(job[0].hard, job[0].money);
        Job pre = job[0];
        for (int i = 1; i < len; i++) {
            if (job[i].hard != pre.hard && job[i].money > pre.money) {
                pre = job[i];
                map.put(pre.hard, pre.money);
            }
        }

        // (4) 给对应ability的人分配工作
        int[] ans = new int[ability.length];
        for (int i = 0; i < ability.length; i++) {
            // 找到map中需要能力小于ability[i] 且距离最近, map.key <= ability[i]
            Integer key = map.floorKey(ability[i]);  // O(logN), 这里可以使用二分查找
            ans[i] = key != null ? map.get(key) : 0;  // 可能这个人能力太差没有任何工作可以cover
        }
        return ans;
    }

    public static class Job {
        public int money;

        public int hard;

        public Job(int m, int h) {
            this.money = m;
            this.hard = h;
        }
    }

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

    public static void main(String[] args) {
        int N = 20;
        int M = 20;
        int maxHard = 10;
        int maxMoney = 15;
        int maxAbility = 20;
        int testTime = 50000;
        System.out.println("test start...");
        for (int i = 0; i < testTime; i++) {
            int[] hard = randomArray(N, maxHard);
            int[] money = randomArray(N, maxMoney);
            int[] ability = randomArray(M, maxAbility);
            int[] ans1 = getMoneys1(hard, money, ability);
            int[] ans2 = getMoneys2(hard, money, ability);

            for (int j = 0; j < ability.length; j++) {
                if (ans1[j] != ans2[j]) {
                    System.out.println("fail");
                }
            }
        }
        System.out.println("test success");
    }
}
