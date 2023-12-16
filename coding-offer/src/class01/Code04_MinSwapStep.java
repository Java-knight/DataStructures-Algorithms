package class01;

/**
 * 一个数组中只有两种字符 'G' 和 'B'，可以让所有的 G 都放在左侧，所有的B都放在右侧，
 * 或者让所有的B都放在左侧，所有的G都放在右侧。但是只能在相邻字符之间进行交换操作，返回至少需要交换几次
 * @author knight
 * @desc
 * @date 2023/12/16
 */
public class Code04_MinSwapStep {

    /**
     * 贪心; 时间复杂度: O(N)
     * 两种情况都跑一边, 然后min一次
     * @param s
     * @return
     */
    public static int minStep1(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        char[] charArr = s.toCharArray();
        // G左 B右
        int gStep = 0;
        int gIndex = 0;
        for (int cur = 0; cur < charArr.length; cur++) {
            if (charArr[cur] == 'G') {
                gStep += cur - (gIndex++);
            }
        }

        // B左 G右
        int bStep = 0;
        int bIndex = 0;
        for (int cur = 0; cur < charArr.length; cur++) {
            if (charArr[cur] == 'B') {
                bStep += cur - (bIndex++);
            }
        }

        return Math.min(gStep, bStep);
    }

    public static int minStep2(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        char[] charArr = s.toCharArray();
        int gStep = 0;
        int bStep = 0;
        int gIndex = 0;
        int bIndex = 0;
        for (int cur = 0; cur < charArr.length; cur++) {
            if (charArr[cur] == 'G') {  // G去左边
                gStep += cur - (gIndex++);
            } else {  // B去左边
                bStep += cur - (bIndex++);
            }
        }
        return Math.min(gStep, bStep);
    }

    // for test
    public static String randomString(int maxLen) {
        char[] str = new char[(int) (Math.random() * maxLen)];
        for (int i = 0; i < str.length; i++) {
            str[i] = Math.random() < 0.5 ? 'G' : 'B';
        }
        return String.valueOf(str);
    }

    public static void main(String[] args) {
        int maxLen = 100;
        int testCount = 1000000;
        System.out.println("test start...");
        for (int i = 0; i < testCount; i++) {
            String str = randomString(maxLen);
            int ans1 = minStep1(str);
            int ans2 = minStep2(str);
            if (ans1 != ans2) {
                System.out.println("fail");
            }
        }
        System.out.println("test end");
    }

}
