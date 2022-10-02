package toplikedquestions;

// 最佳买卖股票时机含冷冻期
// https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-cooldown/
public class Problem_0309_BestTimeToBuyAndSellStockWithCooldown {

    // question: 整个过程能买卖无数次, 但是在卖出后有一天的冷冻期(cooldown)
    // 思路: 保存一下, 第i天两天前卖出sellPre、买入buy、卖出sell他们的最优解, 最后返回sell
    public int maxProfit(int[] prices) {
        int size = prices.length;
        int sellPre = 0;  // 卖出股票(两天前, 已经可以进行买入了)
        int buy = -prices[0];  // 买入股票
        int sell = 0;  // 卖出股票
        for (int i = 1; i < size; i++) {
            int tmp = sell;
            // 这个买入/卖出 就是那一天的冷冻期
            sell = Math.max(sell, buy + prices[i]);
            buy = Math.max(buy, sellPre - prices[i]);
            sellPre = tmp;
        }
        return sell;
    }
}
