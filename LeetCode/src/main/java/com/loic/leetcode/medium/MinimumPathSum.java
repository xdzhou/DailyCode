package com.loic.leetcode.medium;

/**
 * 64. Minimum Path Sum
 * https://leetcode.com/problems/minimum-path-sum/
 * <p>
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right
 * which minimizes the sum of all numbers along its path.
 * <p>
 * Note: You can only move either down or right at any point in time.
 */
public final class MinimumPathSum {

  public static int minSum(int[][] grid) {
    int[][] dp = new int[grid.length][grid[0].length];
    for (int row = 0; row < dp.length; row++) {
      for (int col = 0; col < dp[0].length; col++) {
        int leftCost = (col - 1 >= 0) ? dp[row][col - 1] : Integer.MAX_VALUE;
        int topCost = (row - 1 >= 0) ? dp[row - 1][col] : Integer.MAX_VALUE;
        if (leftCost == topCost && leftCost == Integer.MAX_VALUE) {
          dp[row][col] = grid[row][col];
        } else {
          dp[row][col] = grid[row][col] + Math.min(leftCost, topCost);
        }
      }
    }
    return dp[dp.length - 1][dp[0].length - 1];
  }
}
