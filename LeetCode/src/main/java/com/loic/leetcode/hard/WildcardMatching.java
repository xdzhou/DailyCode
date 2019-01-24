package com.loic.leetcode.hard;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    // pattern p could be split by several segments, separated by *
    // the key of Entry item is the from index of segment, value is the length of segment
    List<Map.Entry<Integer, Integer>> segments = new ArrayList<>();

    int minLen = 0;
    int from = 0;
    for (int i = 0; i < p.length(); i++) {
      if (p.charAt(i) != '*') {
        minLen++;
        if (from < 0) {
          from = i;
        }
      } else {
        if (from >= 0 && i >= from) {
          segments.add(new AbstractMap.SimpleEntry<>(from, i - from));
        }
        from = -1;
      }
    }
    if (s.length() < minLen) {
      return false;
    }

    if (from < 0) {
      from = p.length();
    }
    segments.add(new AbstractMap.SimpleEntry<>(from, p.length() - from));
    // check head segment
    if (!subStringEqual(s, p, 0, 0, segments.get(0).getValue())) {
      return false;
    }
    // check tail segment
    if (segments.size() > 1) {
      Map.Entry<Integer, Integer> entry = segments.get(segments.size() - 1);
      if (!subStringEqual(s, p, s.length() - entry.getValue(), entry.getKey(), entry.getValue())) {
        return false;
      }
    } else {
      // pattern don't contain *
      // here we already check the first/head segment, just make sure the length is equal
      return p.length() == s.length();
    }
    // check other segments
    int indexS = segments.get(0).getValue();
    int toMatchLen = minLen - segments.get(0).getValue();
    for (int i = 1; i < segments.size() - 1; i++) {
      Map.Entry<Integer, Integer> entry = segments.get(i);
      while (indexS < s.length()) {
        if (indexS > s.length() - toMatchLen) {
          return false;
        } else if (subStringEqual(s, p, indexS, entry.getKey(), entry.getValue())) {
          toMatchLen -= entry.getValue();
          indexS += entry.getValue();
          break;
        }
        indexS++;
      }
    }
    // after check all the segment except head & tail segment, the remaining 'toMatchLen' should be the length of tail segment
    return toMatchLen == segments.get(segments.size() - 1).getValue();
  }

  /**
   * check whether the subString s[fromS:fromS+length-1] is equal to p[fromP:fromP+length-1] return
   * true if length is 0
   */
  private static boolean subStringEqual(String s, String p, int fromS, int fromP, int length) {
    for (int i = 0; i < length; i++) {
      char charP = p.charAt(fromP + i);
      if (charP != '?' && charP != s.charAt(fromS + i)) {
        return false;
      }
    }
    return true;
  }
}
