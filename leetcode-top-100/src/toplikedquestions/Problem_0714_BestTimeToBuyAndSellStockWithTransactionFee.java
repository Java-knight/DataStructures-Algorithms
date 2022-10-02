package toplikedquestions;


// 买卖股票的最佳时机含有手续费
// https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
public class Problem_0714_BestTimeToBuyAndSellStockWithTransactionFee {

    // question: 整个过程能买卖无数次, 但是在买入时候需要支付一次手续费, 卖出是不需要的
    // 思路: 在i位置交易的时候, 需要提前保存一下i-1位置的卖出价格, 用于在i位置买入做计算
    // dp[i]sell = max(dp[i-1]sell, dp[i-1]buy+arr[i])
    // dp[i]buy = max(dp[i-1]buy, dp[i-1]sell-arr[i]-fee)
    public int maxProfit(int[] prices, int fee) {
        int sell = 0;
        int buy = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length; i++) {
            int tmp = sell;  // 因为买入会产生手续费, 所以需要提前保存下卖出的价格
            sell = Math.max(sell, buy + prices[i]);  // 卖出
            buy = Math.max(buy, tmp - prices[i] - fee);  // 买入(手续费)
        }
        return sell;
    }
}
