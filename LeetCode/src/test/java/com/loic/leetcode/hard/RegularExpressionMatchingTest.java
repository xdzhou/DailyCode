package com.loic.leetcode.hard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class RegularExpressionMatchingTest {

  @Test
  void empty() {
    assertTrue(RegularExpressionMatching.match("", ""));
    assertFalse(RegularExpressionMatching.match("a", ""));
    assertFalse(RegularExpressionMatching.match("", "a"));
  }

  @Test
  void dot() {
    assertTrue(RegularExpressionMatching.match("a", "."));
    assertTrue(RegularExpressionMatching.match("ab", "a."));
    assertTrue(RegularExpressionMatching.match("abc", "..."));
    assertFalse(RegularExpressionMatching.match("abc", ".."));
  }

  @Test
  void same() {
    assertTrue(RegularExpressionMatching.match("a", "a"));
    assertTrue(RegularExpressionMatching.match("abc", "abc"));
  }

  @Test
  void mix() {
    assertTrue(RegularExpressionMatching.match("", ".*"));
    assertTrue(RegularExpressionMatching.match("a", ".*"));
    assertTrue(RegularExpressionMatching.match("aa", ".*"));
    assertTrue(RegularExpressionMatching.match("ab", ".*"));
  }

  @Test
  void leedCode(){
    assertTrue(RegularExpressionMatching.match("aab", "c*a*b"));
    assertFalse(RegularExpressionMatching.match("mississippi", "mis*is*p*."));
  }
}