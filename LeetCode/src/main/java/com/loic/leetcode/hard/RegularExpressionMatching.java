package com.loic.leetcode.hard;

/**
 * 10. Regular Expression Matching
 * https://leetcode.com/problems/regular-expression-matching/
 * <p>
 * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 */
public final class RegularExpressionMatching {

  public static boolean match(String s, String p) {
    int minLen = 0;
    for (int i = 0; i < p.length(); i++) {
      if (p.charAt(i) == '*') {
        minLen++;
      }
    }
    //minLen is the minimum length of s that p can match
    minLen = p.length() - 2 * minLen;
    if (s.length() < minLen) {
      return false;
    }
    return internalMarch(s, p, s.length() - minLen);
  }

  //maxExpand is the maximum length that '*' can expand
  private static boolean internalMarch(String s, String p, int maxExpand) {
    int indexS = 0, indexP = 0;
    while (indexS < s.length() && indexP < p.length()) {
      //current pattern char
      char curP = p.charAt(indexP);
      //next pattern char
      char nextP = indexP + 1 < p.length() ? p.charAt(indexP + 1) : '?';
      if (curP == '.') {
        if (nextP == '*') {
          for (int expand = 0; expand <= maxExpand; expand++) {
            if (internalMarch(s.substring(indexS + expand), p.substring(indexP + 2), maxExpand - expand)) {
              return true;
            }
          }
          return false;
        } else {
          indexP++;
          indexS++;
        }
      } else {
        if (nextP == '*') {
          if (curP != s.charAt(indexS)) {
            indexP += 2;
          } else {
            int sameLen = sameCharLen(s, indexS);
            int expandRange = Math.min(maxExpand, sameLen);
            for (int expand = 0; expand <= expandRange; expand++) {
              if (internalMarch(s.substring(indexS + expand), p.substring(indexP + 2), maxExpand - expand)) {
                return true;
              }
            }
            return false;
          }
        } else {
          if (curP != s.charAt(indexS)) {
            return false;
          } else {
            indexP++;
            indexS++;
          }
        }
      }
    }
    char nextP = indexP + 1 < p.length() ? p.charAt(indexP + 1) : '?';
    //if s is "", and p is "?*", it is also OK
    return indexS == s.length() && (indexP == p.length() || nextP == '*');
  }

  /**
   * the length of same char at s.charAt(referenceIndex)
   */
  private static int sameCharLen(String s, int referenceIndex) {
    int curIndex = referenceIndex + 1;
    while (curIndex < s.length() && s.charAt(curIndex) == s.charAt(referenceIndex)) {
      curIndex++;
    }
    return curIndex - referenceIndex;
  }
}
