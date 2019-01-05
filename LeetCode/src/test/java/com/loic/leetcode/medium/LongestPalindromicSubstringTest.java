package com.loic.leetcode.medium;

import com.loic.leetcode.SolutionChecker;
import org.junit.jupiter.api.Test;

class LongestPalindromicSubstringTest {
  private final SolutionChecker<String, String> checker = SolutionChecker.create(LongestPalindromicSubstring::find, LongestPalindromicSubstring::manacher);

  @Test
  void find() {
    checker.check("", "")
      .check("babad", "bab", "aba")
      .check("cbbd", "bb")
      .check("banana", "anana");
  }
}