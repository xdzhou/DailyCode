package com.loic.leetcode.easy;

/**
 * 14. Longest Common Prefix
 * https://leetcode.com/problems/longest-common-prefix/
 * <p>
 * Write a function to find the longest common prefix string amongst an array of strings.
 * <p>
 * If there is no common prefix, return an empty string "".
 */
public final class LongestCommonPrefix {

  public static String find(String... strs) {
    if (strs.length == 1) {
      return strs[0];
    }
    int curIndex = 0;
    while (true) {
      char c = getChar(strs[0], curIndex);
      for (int i = 1; i < strs.length; i++) {
        if (getChar(strs[i], curIndex) != c) {
          return strs[0].substring(0, curIndex);
        }
      }
      if (c == '*') {
        break;
      }
      curIndex++;
    }
    return strs[0].substring(0, curIndex);
  }

  private static char getChar(String s, int index) {
    return index < s.length() ? s.charAt(index) : '*';
  }
}
