package com.loic.leetcode.hard;

import com.loic.leetcode.SolutionChecker;
import org.junit.jupiter.api.Test;

class ShortestPalindromeTest {
  private static final String BIG = bigString();

  @Test
  void simple() {
    SolutionChecker.create(ShortestPalindrome::shortestPalindrome, ShortestPalindrome::shortestPalindrome2, ShortestPalindrome::shortestPalindrome3)
      .check("", "")
      .check("a", "a")
      .check("aa", "aa")
      .check("aba", "aba")
      .check("aacecaaa", "aaacecaaa")
      .check("abcd", "dcbabcd")
      .check(BIG, BIG);
  }

  private static String bigString() {
    StringBuilder sb = new StringBuilder(10_000);
    for (int i = 0; i < 10_000; i++) {
      sb.append('a');
    }
    return sb.toString();
  }
}