package class18;



/**
 * 分析: https://www.processon.com/diagraming/6441756b84b4b71c14facc8e
 * @desc
 * @date 2023/4/21
 */
public class Code01_RobotWalk {

    // 尝试模型1(暴力递归)
    public static int ways1(int N, int M, int P, int K) {
        if (N < 2 || M < 1 || M > N || P < 1 || K < 1) {
            return -1;
        }
        return process1(N, M, P, K);
    }

    /**
     * @param nums 一共有 nums个节点
     * @param cur 当前位置
     * @param end   结束位置
     * @param count 必须要走的步数
     * @return
     */
    public static int process1(int nums, int cur, int end, int count) {
        if (count == 0) {  // base case1: 如果当前步数用完了, 判断是否走成功
            return cur == end ? 1 : 0;
        }

        if (cur == 1) {  // 左边界[剪纸: 防止越界]
            return process1(nums, 2, end, count - 1);
        }
        if (cur == nums) {  // 右边界[剪纸: 防止越界]
            return process1(nums, nums - 1, end, count - 1);
        }

        // base case2: 中间位置(向左/向右)
        return process1(nums, cur - 1, end, count - 1) + process1(nums, cur + 1, end, count - 1);
    }

    // 尝试模型2(基础dp)
    public static int ways2(int N, int M, int P, int K) {
        if (N < 2 || M < 1 || M > N || P < 1 || K < 1) {
            return -1;
        }
        int[][] dp = new int[N + 1][K + 1];
        // init
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                dp[i][j] = -1;
            }
        }
        return process2(N, M, P, K, dp);
    }

    public static int process2(int nums, int cur, int end, int count, int[][] dp) {
        if (dp[cur][count] != -1) {
            return dp[cur][count];
        }

        // 之前没有算过
        int ans = 0;
        if (count == 0) {  // 步数用完了
            ans = cur == end ? 1 : 0;
        } else if (cur == 1) {  // 左边界
            ans = process2(nums, cur + 1, end, count - 1, dp);
        } else if (cur == nums) {  // 右边界
            ans = process2(nums, cur - 1, end, count - 1, dp);
        } else {  // 在中间, 非左/右边界
            ans = process2(nums, cur - 1, end, count - 1, dp) + process2(nums, cur + 1, end, count - 1, dp);
        }
        dp[cur][count] = ans;
        return ans;
    }

    // 尝试模型3(最终dp)
    public static int ways3(int N, int M, int P, int K) {
        if (N < 2 || M < 1 || M > N || P < 1 || K < 1) {
            return -1;
        }
        int[][] dp = new int[N + 1][K + 1];
        dp[P][0] = 1;  // base case1
        for (int i = 1; i <= K; i++) {
            dp[1][i] = dp[2][i - 1];  // base case2 左下角
            for (int j = 2; j < N; j++) {
                dp[j][i] = dp[j + 1][i - 1] + dp[j - 1][i - 1];  // 左下角+左上角
            }
            dp[N][i] = dp[N - 1][i - 1];  // base case3  左上角
        }
        return dp[M][K];
    }


    public static void main(String[] args) {
        System.out.println(ways1(7, 2, 4, 4));
        System.out.println(ways2(7, 2, 4, 4));
        System.out.println(ways3(7, 2, 4, 4));
    }
}
