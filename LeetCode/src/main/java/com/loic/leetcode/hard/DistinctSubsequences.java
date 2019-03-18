package com.loic.leetcode.hard;

import java.util.Arrays;

/**
 * 115. Distinct Subsequences
 * https://leetcode.com/problems/distinct-subsequences/
 *
 * Given a string S and a string T, count the number of distinct subsequences of S which equals T.
 *
 * A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
 *
 * Example 1:
 *
 * Input: S = "rabbbit", T = "rabbit"
 * Output: 3
 * Explanation:
 *
 * As shown below, there are 3 ways you can generate "rabbit" from S.
 * (The caret symbol ^ means the chosen letters)
 *
 * rabbbit
 * ^^^^ ^^
 * rabbbit
 * ^^ ^^^^
 * rabbbit
 * ^^^ ^^^
 */
public class DistinctSubsequences {

  public static int numDistinct(String s, String t) {
    int[] memo = new int[(s.length() + 1) * (t.length() + 1)];
    Arrays.fill(memo, -1);
    return numDistinct(s, 0, t, 0, memo);
  }

  /**
   * count the number of distinct subsequences of S[indexS:] which equals T[indexT:]
   */
  private static int numDistinct(String s, int indexS, String t, int indexT, int[] memo) {
    int memoIndex = indexS * (t.length() + 1) + indexT;
    if (memo[memoIndex] >= 0) {
      return memo[memoIndex];
    }
    int sum = 0;
    if (indexT == t.length()) {
      sum = 1;
    } else {
      int maxDelta = (s.length() - indexS) - (t.length() - indexT);
      for (int delta = 0; delta <= maxDelta; delta++) {
        if (s.charAt(indexS + delta) == t.charAt(indexT)) {
          sum += numDistinct(s, indexS + delta + 1, t, indexT + 1, memo);
        }
      }
    }
    memo[memoIndex] = sum;
    return sum;
  }


  public static int dpSolution(String s, String t) {
    // dp[i][j] means the number of distinct subsequences of S[-1:i) which equals T[-1:j)
    // as in tht 2D dp solution, we only use previous dp row information, we can creat a 2 rows dp array
    int[][] dp = new int[2][t.length() + 1];
    for (int i = 0; i <= s.length(); i++) {
      int preRow = (i - 1) % 2;
      int row = i % 2;
      for (int j = 0; j <= t.length(); j++) {
        if (j == 0) {
          dp[row][j] = 1;
        } else if (i == 0) {
          dp[row][j] = 0;
        } else {
          if (s.charAt(i - 1) == t.charAt(j - 1)) {
            dp[row][j] = dp[preRow][j - 1] + dp[preRow][j];
          } else {
            dp[row][j] = dp[preRow][j];
          }
        }
      }
    }
    return dp[s.length() % 2][t.length()];
  }
}
