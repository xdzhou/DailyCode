package com.loic.leetcode.hard;

/**
 * 32. Longest Valid Parentheses
 * https://leetcode.com/problems/longest-valid-parentheses/
 * <p>
 * Given a string containing just the characters '(' and ')',
 * find the length of the longest valid (well-formed) parentheses substring.
 */
public final class LongestValidParentheses {

  public static int find(String s) {
    int max = 0;
    //dp[i] is the longest valid parentheses length which ended at index i
    int[] dp = new int[s.length()];
    for (int i = 1; i < s.length(); i++) {
      char c = s.charAt(i);
      //a valid parentheses must end with ')'
      if (c == ')') {
        if (s.charAt(i - 1) == '(') {
          // if previous char is '(', then s[i-1,i] is a valid parentheses
          // and we try to contact previous potential valid parentheses which end at index 'i-2'
          dp[i] = 2 + getDp(dp, i - 2);
        } else {
          if (dp[i - 1] == 0) {
            dp[i] = 0;
          } else {
            // we found a valid parentheses end at index 'i-1'
            // check s[i-dp[i-1]-1, i] substring could be a valid parentheses
            int opponent = i - dp[i - 1] - 1;
            // we also need to contact previous potential valid parentheses which end at index 'opponent - 1'
            dp[i] = getChar(s, opponent) == '(' ? dp[i - 1] + 2 + getDp(dp, opponent - 1) : 0;
          }
        }
        max = Math.max(max, dp[i]);
      }
    }
    return max;
  }

  private static char getChar(String s, int index) {
    return index >= 0 ? s.charAt(index) : '?';
  }

  private static int getDp(int[] dp, int index) {
    return index >= 0 ? dp[index] : 0;
  }
}
