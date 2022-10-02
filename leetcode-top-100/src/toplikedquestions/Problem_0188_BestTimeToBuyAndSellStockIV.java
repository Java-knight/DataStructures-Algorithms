package toplikedquestions;



// 买卖股票的最佳时机III
// https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iv/
public class Problem_0188_BestTimeToBuyAndSellStockIV {

    // question: 最多买卖k次, 求最优解
    // 必须先写注释掉的dp方法, 才能推到出空间压缩的dp方法
    // 思路: 1.如果k >= arr.length/2, 我们可以认为是可以进行无限次买卖的, 和股票问题II相同
    // 2.否则, 需要dp解决。那么在i位置, 我们都能进行那些操作:
    // dp[i][j]: 从0~i位置进行了最多j次操作的最优解
    //   (1) i位置不进行任何买卖操作, dp[i][j] = dp[i-1][j]
    //   (2) i位置进行买卖操作, 枚举操作
    //   dp[i][j] = max(dp[i][j-1]+arr[i]-arr[i], dp[i-1][j-1]+arr[i]-arr[i-1],..., dp[0][j-1]+arr[i]-arr[0])
    //   dp[i][j-1]+arr[i]-arr[i]: 是在i位置进行了买入和卖出操作;(获取一下减少一次购买次数的最优解)
    //   dp[i-1][j-1]+arr[i]-arr[i-1]: 是在i-1位置进行了买入操作, 在i位置进行了卖出操作
    //   在把(1)和(2)的结果求max就是dp[i][j]的最优解了
    // 最终结果: dp[arr.length-1][k]
    // 斜率优化(空间压缩): 在2中的(2)中出现重复的枚举操作, 可以压缩
    public int maxProfit(int k, int[] prices) {
        int size = prices.length;
        if (k >= (size >> 1)) {  // k >= size/2 时, 无限次买卖
            return allTimes(prices);
        }
        int[][] dp = new int[k + 1][size];
        // dp[0][...]无效值, 在arr[0]上无论怎么进行买卖没有意义
        // dp[...][0]无效值, 剩余买卖次数为0了, 已经不能交易了
        for (int j = 1; j <= k; j++) {
            int pre = dp[j][0];
            int best = pre - prices[0];  // 进行压缩枚举的max
            for (int i = 1; i < size; i++) {
                pre = dp[j - 1][i];
                // 求i位置(1)和(2)的max; (1) i位置不进行任何买卖操作  (2) i位置进行卖操作, 枚举操作进行了压缩
                dp[j][i] = Math.max(dp[j][i - 1], prices[i] + best);
                best = Math.max(best, pre - prices[i]);
            }
        }
        return dp[k][size - 1];
    }

    // 无限次买卖最优解
    public int allTimes(int[] prices) {
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            result += Math.max(prices[i] - prices[i - 1], 0);
        }
        return result;
    }

//    // question: 最多买卖k次, 求最优解
//    // 思路: 1.如果k >= arr.length/2, 我们可以认为是可以进行无限次买卖的, 和股票问题II相同
//    // 2.否则, 需要dp解决。那么在i位置, 我们都能进行那些操作:
//    // dp[i][j]: 从0~i位置进行了最多j次操作的最优解
//    //   (1) i位置不进行任何买卖操作, dp[i][j] = dp[i-1][j]
//    //   (2) i位置进行买卖操作, 枚举操作
//    //   dp[i][j] = max(dp[i][j-1]+arr[i]-arr[i], dp[i-1][j-1]+arr[i]-arr[i-1],..., dp[0][j-1]+arr[i]-arr[0])
//    //   dp[i][j-1]+arr[i]-arr[i]: 是在i位置进行了买入和卖出操作;(获取一下减少一次购买次数的最优解)
//    //   dp[i-1][j-1]+arr[i]-arr[i-1]: 是在i-1位置进行了买入操作, 在i位置进行了卖出操作
//    //   在把(1)和(2)的结果求max就是dp[i][j]的最优解了
//    // 最终结果: dp[arr.length-1][k]
//    public int maxProfit(int k, int[] prices) {
//        int size = prices.length;
//        if (k >= (size >> 1)) {  // k >= size/2 时, 无限次买卖
//            return allTimes(prices);
//        }
//        int[][] dp = new int[size][k + 1];
//        // dp[0][...]无效值, 在arr[0]上无论怎么进行买卖没有意义
//        // dp[...][0]无效值, 剩余买卖次数为0了, 已经不能交易了
//        for (int i = 1; i < size; i++) {
//            for (int j = 1; j <= k; j++) {
//                // (1) i位置不进行任何买卖操作
//                dp[i][j] = dp[i - 1][j];
//                // (2) i位置进行买卖操作, 枚举操作
//                for (int p = 0; p <= i; p++) {
//                    dp[i][j] = Math.max(dp[i][j], dp[p][j - 1] + prices[i] - prices[p]);
//                }
//            }
//        }
//        return dp[size - 1][k];
//    }
}
