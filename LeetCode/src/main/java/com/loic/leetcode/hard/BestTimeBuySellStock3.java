package com.loic.leetcode.hard;

/**
 * 123. Best Time to Buy and Sell Stock III
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 *
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
 *
 * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
 *
 * Example 1:
 *
 * Input: [3,3,5,0,0,3,1,4]
 * Output: 6
 * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 *              Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
 */
public class BestTimeBuySellStock3 {

  /**
   * max profit = max(0 transaction, 1 transaction, 2 transactions),
   * the profit of 0 transaction is 0
   */
  public static int maxProfit(int... prices) {
    if (prices.length < 2) {
      return 0;
    }
    int profit = 0;
    // the max profit when we buy at day i
    int[] maxBuy = new int[prices.length];
    // the max profit when we sell at day i
    int[] maxSell = new int[prices.length];
    int min = prices[0];
    for (int i = 1; i < prices.length; i++) {
      maxSell[i] = prices[i] - min;
      min = Math.min(min, prices[i]);
      // compute the profit of 1 transaction
      profit = Math.max(profit, maxSell[i]);
    }
    int max = prices[prices.length - 1];
    for (int i = prices.length - 2; i >= 0; i--) {
      maxBuy[i] = max - prices[i];
      max = Math.max(max, prices[i]);
    }
    // until now 'profit' is the max(0 transaction, 1 transaction)
    // we need get the max profit of 2 transactions

    //now 'maxSell' means : the max profit we could have if we sell at day i or before day i
    for (int i = 2; i < prices.length; i++) {
      maxSell[i] = Math.max(maxSell[i], maxSell[i - 1]);
    }
    //now 'maxBuy' means : the max profit we could have if we buy at day i or after day i
    for (int i = prices.length - 3; i >= 0; i--) {
      maxBuy[i] = Math.max(maxBuy[i], maxBuy[i + 1]);
    }
    // if we do 2 transactions
    for (int i = 1; i <= prices.length - 3; i++) {
      // we do one transaction between[0,i] and another between(i:]
      profit = Math.max(profit, maxSell[i] + maxBuy[i + 1]);
    }
    return profit;
  }
}
