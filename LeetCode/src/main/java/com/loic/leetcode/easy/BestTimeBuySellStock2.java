package com.loic.leetcode.easy;

/**
 * 122. Best Time to Buy and Sell Stock II
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 *
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
 *
 * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
 *
 * Example 1:
 *
 * Input: [7,1,5,3,6,4]
 * Output: 7
 * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 *              Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
 */
public class BestTimeBuySellStock2 {

  /**
   * get all the increase sub-array, and sum up the (peak-valley).
   * buy at first item of increase sub-array, and sell at last item of increase sub-array
   */
  public static int maxProfit(int... prices) {
    int sum = 0;
    int curMin = prices.length == 0 ? 0 : prices[0];
    for (int i = 1; i <= prices.length; i++) {
      // ATTENTION: special case, prices are increasing array
      int p = (i == prices.length) ? 0 : prices[i];
      if (i == prices.length || prices[i] < prices[i - 1]) {
        if (prices[i - 1] > curMin) {
          sum += (prices[i - 1] - curMin);
        }
        curMin = p;
      }
    }
    return sum;
  }
}
