package com.loic.leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LongestPalindromicSubstringTest {

  @Test
  void find() {
    Assertions.assertEquals("", LongestPalindromicSubstring.find(""));
    Assertions.assertEquals("aba", LongestPalindromicSubstring.find("babad"));
    Assertions.assertEquals("bb", LongestPalindromicSubstring.find("cbbd"));
    Assertions.assertEquals("anana", LongestPalindromicSubstring.find("banana"));
  }
}