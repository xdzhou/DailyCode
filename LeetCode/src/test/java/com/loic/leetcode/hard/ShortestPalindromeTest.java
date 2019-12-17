package com.loic.leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ShortestPalindromeTest {
  private static final String BIG = bigString();

  @Test
  void simple() {
    Assertions.assertEquals("", ShortestPalindrome.shortestPalindrome(""));
    Assertions.assertEquals("a", ShortestPalindrome.shortestPalindrome("a"));
    Assertions.assertEquals("aa", ShortestPalindrome.shortestPalindrome("aa"));
    Assertions.assertEquals("aba", ShortestPalindrome.shortestPalindrome("aba"));
    Assertions.assertEquals("aaacecaaa", ShortestPalindrome.shortestPalindrome("aacecaaa"));
    Assertions.assertEquals("dcbabcd", ShortestPalindrome.shortestPalindrome("abcd"));
  }

  @Test
  void simple2() {
    Assertions.assertEquals("", ShortestPalindrome.shortestPalindrome2(""));
    Assertions.assertEquals("a", ShortestPalindrome.shortestPalindrome2("a"));
    Assertions.assertEquals("aa", ShortestPalindrome.shortestPalindrome2("aa"));
    Assertions.assertEquals("aba", ShortestPalindrome.shortestPalindrome2("aba"));
    Assertions.assertEquals("aaacecaaa", ShortestPalindrome.shortestPalindrome2("aacecaaa"));
    Assertions.assertEquals("dcbabcd", ShortestPalindrome.shortestPalindrome2("abcd"));
  }

  @Test
  void test1() {
    Assertions.assertEquals(BIG, ShortestPalindrome.shortestPalindrome(BIG));
  }

  @Test
  void test2() {
    Assertions.assertEquals(BIG, ShortestPalindrome.shortestPalindrome2(BIG));
  }

  private static String bigString() {
    StringBuilder sb = new StringBuilder(10_000);
    for (int i = 0; i < 10_000; i++) {
      sb.append('a');
    }
    return sb.toString();
  }
}