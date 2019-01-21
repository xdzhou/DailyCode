package com.loic.leetcode.hard;

/**
 * 44. Wildcard Matching
 * https://leetcode.com/problems/wildcard-matching/
 * <p>
 * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.
 * <p>
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * The matching should cover the entire input string (not partial).
 * <p>
 * Note:
 * <p>
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters like ? or *.
 */
public final class WildcardMatching {

  public static boolean isMatch(String s, String p) {
    int minLen = 0;
    for (int i = 0; i < p.length(); i++) {
      if (p.charAt(i) != '*') {
        minLen++;
      }
    }
    if (s.length() < minLen) {
      return false;
    }
    return internalMatch(s, p, s.length() - minLen);
  }

  private static boolean internalMatch(String s, String p, int maxExpand) {
    int indexS = 0, indexP = 0;
    while (indexS < s.length() && indexP < p.length()) {
      char curP = p.charAt(indexP);
      if (curP == '?') {
        indexP++;
        indexS++;
      } else if (curP == '*') {
        for (int expand = 0; expand <= maxExpand; expand++) {
          if (internalMatch(s.substring(indexS + expand), p.substring(lastStarIndex(p, indexP)), maxExpand - expand)) {
            return true;
          }
        }
        return false;
      } else {
        if (s.charAt(indexS) != curP) {
          return false;
        } else {
          indexP++;
          indexS++;
        }
      }
    }
    return indexS == s.length() && (indexP == p.length() || p.charAt(indexP) == '*');
  }

  //skip all continues star
  private static int lastStarIndex(String s, int index) {
    while (index < s.length() && s.charAt(index) == '*') {
      index++;
    }
    return index;
  }
}
