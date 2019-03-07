package com.loic.leetcode.medium;

import java.util.Arrays;

/**
 * 91. Decode Ways
 * https://leetcode.com/problems/decode-ways/
 *
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given a non-empty string containing only digits, determine the total number of ways to decode it.
 *
 * Example 1:
 *
 * Input: "12"
 * Output: 2
 * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
 */
public final class DecodeWays {

  public static int num(String s) {
    if (s.isEmpty()) {
      return 0;
    }
    int[] dp = new int[s.length()];
    Arrays.fill(dp, -1);
    return num(s, 0, dp);
  }

  private static int num(String s, int from, int[] dp) {
    // be careful of '0', only 10 20 is allowed
    if (from < s.length() && s.charAt(from) - '0' == 0) {
      return 0;
    } else if (from >= s.length() - 1) {
      return 1;
    }
    if (dp[from] < 0) {
      // parse the number of s[from, from+1]
      int num = s.charAt(from + 1) - '0';
      num += 10 * (s.charAt(from) - '0');
      if (num > 26) {
        dp[from] = num(s, from + 1, dp);
      } else {
        dp[from] = num(s, from + 1, dp) + num(s, from + 2, dp);
      }
    }
    return dp[from];
  }
}
