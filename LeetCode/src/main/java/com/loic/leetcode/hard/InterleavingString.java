package com.loic.leetcode.hard;

import java.util.Arrays;

/**
 * 97. Interleaving String
 * https://leetcode.com/problems/interleaving-string/
 * <p>
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 * <p>
 * Example 1:
 * <p>
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * Output: true
 * Example 2:
 * <p>
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * Output: false
 */
public final class InterleavingString {

  public static boolean isInterleave(String s1, String s2, String s3) {
    if (s1.length() + s2.length() != s3.length()) {
      return false;
    }
    int[] memo = new int[(s1.length() + 1) * (s2.length() + 1)];
    Arrays.fill(memo, -1);
    return isInterleave(s1, 0, s2, 0, s3, memo);
  }

  /**
   * check whether s3[p1+p2:] is formed by interleaving of s1[p1:] and s2[p2:]
   * ATTENTION: for recursive method, try to cache result
   *
   * @param p1 the head index of s1
   * @param p2 the head index of s2
   */
  private static boolean isInterleave(String s1, int p1, String s2, int p2, String s3, int[] memo) {
    int memoIndex = p1 * s2.length() + p2;
    if (memo[memoIndex] != -1) {
      return memo[memoIndex] == 1;
    }
    int p3 = p1 + p2;
    while (p3 < s3.length()) {
      char c3 = s3.charAt(p3);
      char c1 = p1 < s1.length() ? s1.charAt(p1) : (char) (c3 - 1);
      char c2 = p2 < s2.length() ? s2.charAt(p2) : (char) (c3 + 1);
      if (c3 != c1 && c3 != c2) {
        memo[memoIndex] = 0;
        return false;
      }
      if (c1 == c2) {
        if (isInterleave(s1, p1 + 1, s2, p2, s3, memo)) {
          memo[memoIndex] = 1;
          return true;
        } else {
          boolean retVal = isInterleave(s1, p1, s2, p2 + 1, s3, memo);
          memo[memoIndex] = retVal ? 1 : 0;
          return retVal;
        }
      }
      if (c1 == c3) {
        p1++;
      } else {
        p2++;
      }
      p3++;
    }
    memo[memoIndex] = 1;
    return true;
  }
}
