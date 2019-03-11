package com.loic.leetcode.medium;

import java.util.Arrays;

/**
 * 96. Unique Binary Search Trees
 * https://leetcode.com/problems/unique-binary-search-trees/
 *
 * Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?
 *
 * Example:
 *
 * Input: 3
 * Output: 5
 * Explanation:
 * Given n = 3, there are a total of 5 unique BST's:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 */
public class UniqueBST {

  public static int numTrees(int n) {
    if (n <= 0) {
      return 0;
    }
    // catalan number
    int[] dp = new int[n + 1];
    Arrays.fill(dp, -1);
    dp[0] = dp[1] = 1;
    return compute(n, dp);
  }

  private static int compute(int n, int[] dp) {
    if (dp[n] < 0) {
      int sum = 0;
      for (int i = 0; i < n; i++) {
        sum += compute(i, dp) * compute(n - 1 - i, dp);
      }
      dp[n] = sum;
    }
    return dp[n];
  }
}
