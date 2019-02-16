package com.loic.leetcode.medium;

/**
 * 62. Unique Paths
 * https://leetcode.com/problems/unique-paths/
 * <p>
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * <p>
 * The robot can only move either down or right at any point in time.
 * The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * <p>
 * How many possible unique paths are there?
 */
public final class UniquePaths {

  public static int count(int m, int n) {
    // compute C(m+n-2, m-1)
    if (n < m) {
      return count(n, m);
    }
    // use long to avoid overflow
    long result = 1;
    for (int i = 1; i <= m - 1; i++) {
      result *= (m + n - 1 - i);
      result /= (i);
    }
    return (int) result;
  }
}
