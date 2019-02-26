package com.loic.leetcode.medium;

/**
 * 63. Unique Paths II
 * https://leetcode.com/problems/unique-paths-ii/
 * <p>
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * <p>
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * <p>
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 * <p>
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 * <p>
 * Note: m and n will be at most 100.
 */
public class UniquePaths2 {

  public static int find(int[][] obstacleGrid) {
    if (obstacleGrid.length == 0 || obstacleGrid[0].length == 0 || obstacleGrid[0][0] == 1) {
      return 0;
    }
    //DP solution, use directly the 'obstacleGrid' as DP array
    int[][] dp = obstacleGrid;
    dp[0][0] = 1;
    for (int i = 0; i < obstacleGrid.length; i++) {
      for (int j = 0; j < obstacleGrid[0].length; j++) {
        if (i != 0 || j != 0) {
          if (obstacleGrid[i][j] == 1) {
            dp[i][j] = 0;
          } else {
            int fromLeft = j - 1 >= 0 ? dp[i][j - 1] : 0;
            int fromTop = i - 1 >= 0 ? dp[i - 1][j] : 0;
            dp[i][j] = fromLeft + fromTop;
          }
        }
      }
    }
    return dp[dp.length - 1][dp[0].length - 1];
  }
}
