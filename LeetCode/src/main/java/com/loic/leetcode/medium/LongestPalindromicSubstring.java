package com.loic.leetcode.medium;

/**
 * 5. Longest Palindromic Substring
 * https://leetcode.com/problems/longest-palindromic-substring/
 * <p>
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 */
public class LongestPalindromicSubstring {

  public static String find(String s) {
    if (s.isEmpty()) {
      return s;
    }
    int from = 0, to = 0;
    //dp(i, j) is true if s.subString(i, j+1) is palindromic
    boolean[][] dp = new boolean[s.length()][s.length()];
    //ATTENTION: here we can't increase "i" from 0 to s.length()-1
    //because "dp[i + 1][j - 1]" isn't computed when we are in "i" step
    for (int i = s.length() - 1; i >= 0; i--) {
      for (int j = i; j < s.length(); j++) {
        if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i + 1][j - 1])) {
          dp[i][j] = true;
          if (j - i > to - from) {
            from = i;
            to = j;
          }
        } else {
          dp[i][j] = false;
        }
      }
    }
    return s.substring(from, to + 1);
  }
}
