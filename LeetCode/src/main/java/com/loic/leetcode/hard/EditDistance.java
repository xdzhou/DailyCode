package com.loic.leetcode.hard;

/**
 * 72. Edit Distance
 * https://leetcode.com/problems/edit-distance/
 * <p>
 * Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
 * <p>
 * You have the following 3 operations permitted on a word:
 * <p>
 * Insert a character
 * Delete a character
 * Replace a character
 */
public final class EditDistance {

  public static int minDistance(String word1, String word2) {
    int[][] dp = new int[word1.length() + 1][word2.length() + 1];
    for (int i = 0; i < word1.length() + 1; i++) {
      for (int j = 0; j < word2.length() + 1; j++) {
        if (i == 0 || j == 0) {
          dp[i][j] = Math.max(i, j);
        } else {
          int dis1 = dp[i - 1][j - 1] + (word1.charAt(i - 1) == word2.charAt(j - 1) ? 0 : 1);
          int dis2 = dp[i - 1][j] + 1;
          int dis3 = dp[i][j - 1] + 1;
          dp[i][j] = Math.min(dis1, Math.min(dis2, dis3));
        }
      }
    }
    return dp[word1.length()][word2.length()];
  }
}
