package com.loic.leetcode.medium;

import com.loic.leetcode.SolutionChecker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LongestPalindromicSubstringTest {
  private final SolutionChecker<String, String> checker = SolutionChecker.create(LongestPalindromicSubstring::find, LongestPalindromicSubstring::manacher);

  @Test
  void find() {
    checker.check("", "")
      .check("babad", (i, o) -> {
        if (!o.equals("bab") && !o.equals("aba")) {
          Assertions.fail("");
        }
      })
      .check("cbbd", "bb")
      .check("banana", "anana");
  }
}