package com.loic.leetcode.hard;

/**
 * 76. Minimum Window Substring
 * https://leetcode.com/problems/minimum-window-substring/
 * <p>
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 * Example:
 * <p>
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 */
public final class MinimumWindowSubstring {

  public static String window(String s, String t) {
    int[] map = new int[256];
    for (char c : t.toCharArray()) {
      map[c]++;
    }
    int from = 0, to = 0;
    // 'count' is the remaining char count of s[from:to) that
    int count = t.length();
    int start = -1, len = Integer.MAX_VALUE;
    while (to < s.length()) {
      // expand 'to' index to right
      if (count > 0 && map[s.charAt(to++)]-- > 0) {
        count--;
      }
      while (count == 0) {
        if (to - from < len) {
          len = to - from;
          start = from;
        }
        // reduce 'from' index to right
        if (map[s.charAt(from++)]++ == 0) {
          count++;
        }
      }
    }
    return start == -1 ? "" : s.substring(start, start + len);
  }
}
