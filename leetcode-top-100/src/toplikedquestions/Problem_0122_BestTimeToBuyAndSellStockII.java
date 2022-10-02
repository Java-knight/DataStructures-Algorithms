package toplikedquestions;


// 买卖股票的最佳时机II
// https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/
public class Problem_0122_BestTimeToBuyAndSellStockII {

    // question: 整个过程能买卖无数次(但是每次手里只能持有一股 ——> 买了等卖了才能第二次买)
    // 思路: 在i位置, 用arr[i] - arr[i-1] > 0, 表示需要买入
    public int maxProfit(int[] prices) {
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            int ans = prices[i] - prices[i - 1];
            if (ans > 0) {  // 收集答案
                result += ans;
            }
        }
        return result;
    }

}
