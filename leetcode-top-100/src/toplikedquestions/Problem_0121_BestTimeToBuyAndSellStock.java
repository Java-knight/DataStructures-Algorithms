package toplikedquestions;


// 买卖股票的最佳时机
// https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/
public class Problem_0121_BestTimeToBuyAndSellStock {

    // question: 整个过程只能买卖一次
    // 思路: 假设第i天卖出, 那么就是0~i天最少价格的一天买入min(arr[0~i]);
    // 最终结果就是max(arr[0~len-1])卖出的结果
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int price : prices) {
            min = Math.min(min, price);  // 获取arr[0~i]上的最小值
            int ans = price - min;  // 保存第i天卖出的最优结果
            max = Math.max(max, ans);  // 收集最终
        }
        return max;
    }

}
