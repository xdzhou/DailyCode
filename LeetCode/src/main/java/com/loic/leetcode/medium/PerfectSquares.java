package com.loic.leetcode.medium;

/**
 * 279. Perfect Squares
 * https://leetcode.com/problems/perfect-squares/
 * <p>
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 12
 * Output: 3
 * Explanation: 12 = 4 + 4 + 4.
 * Example 2:
 * <p>
 * Input: n = 13
 * Output: 2
 * Explanation: 13 = 4 + 9.
 */
public class PerfectSquares {

  public static int numSquares(int n) {
    int[] dp = new int[n + 1];
    dp[0] = 0;
    for (int i = 1; i <= n; i++) {
      int min = i;
      for (int j = 1; j * j <= i; j++) {
        min = Math.min(min, 1 + dp[i - j * j]);
      }
      dp[i] = min;
    }
    return dp[n];
  }
}
