package com.loic.leetcode.hard;

/**
 * 132. Palindrome Partitioning II
 * https://leetcode.com/problems/palindrome-partitioning-ii/
 * <p>
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * <p>
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * <p>
 * Example:
 * <p>
 * Input: "aab"
 * Output: 1
 * Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
 */
public class PalindromePartitioning2 {

  public static int minCut(String s) {
    if (s.length() < 2) {
      return 0;
    }
    // minCut of s[i, s.len-1]
    int[] dp = new int[s.length()];
    boolean[][] isPalindrome = new boolean[s.length()][s.length()];

    for (int left = s.length() - 1; left >= 0; left--) {
      dp[left] = s.length() - 1 - left;
      for (int right = s.length() - 1; right >= left; right--) {
        if (s.charAt(left) == s.charAt(right)) {
          isPalindrome[left][right] = (right - left) <= 2 || isPalindrome[left + 1][right - 1];
        } else {
          isPalindrome[left][right] = false;
        }
        if (isPalindrome[left][right]) {
          dp[left] = right == s.length() - 1 ? 0 : Math.min(1 + dp[right + 1], dp[left]);
        }
      }
    }
    return dp[0];
  }
}
