package com.loic.leetcode.hard;

import java.util.LinkedList;

/**
 * 32. Longest Valid Parentheses
 * https://leetcode.com/problems/longest-valid-parentheses/
 * <p>
 * Given a string containing just the characters '(' and ')',
 * find the length of the longest valid (well-formed) parentheses substring.
 */
public final class LongestValidParentheses {

  public static int stackResolve(String s) {
    int max = 0;
    LinkedList<Integer> stack = new LinkedList<>();
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '(') {
        if (stack.isEmpty()) {
          // the 'i-1' pushed is used as reference, in order to compute valid Parentheses length
          stack.push(i - 1);
        }
        stack.push(i);
      } else {
        if (!stack.isEmpty()) {
          stack.pop();
        }
        if (!stack.isEmpty()) {
          // valid Parentheses length is 'i-stack.peek()', that's why we need push 'i-1' at the beginning
          max = Math.max(max, i - stack.peek());
        }
      }
    }
    return max;
  }

  public static int resolve(String s) {
    int left = 0, right = 0;
    int max = 0;
    // scan from left to right, and make sure 'left' is always >= 'right'
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '(') {
        left++;
      } else {
        right++;
      }
      if (left == right) {
        max = Math.max(max, left + right);
      } else if (right > left) {
        left = right = 0;
      }
    }
    left = right = 0;
    // scan from right to left, and make sure 'right' is always >= 'left'
    for (int i = s.length() - 1; i >= 0; i--) {
      if (s.charAt(i) == '(') {
        left++;
      } else {
        right++;
      }
      if (left == right) {
        max = Math.max(max, left + right);
      } else if (left > right) {
        left = right = 0;
      }
    }
    // we need scan twice as we may never meet the condition "left==right" to update 'max'
    // Maybe the 'left' always >= 'right', when scan from left to right
    // ex. "(()()("
    return max;
  }

  public static int dpResolve(String s) {
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
            // check if s[i-dp[i-1]-1, i] substring could be a valid parentheses
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
