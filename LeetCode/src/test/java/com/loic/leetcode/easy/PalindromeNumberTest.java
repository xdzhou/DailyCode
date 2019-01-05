package com.loic.leetcode.easy;

import com.loic.leetcode.SolutionChecker;
import org.junit.jupiter.api.Test;

class PalindromeNumberTest {
  private final SolutionChecker<Integer, Boolean> checker =
    SolutionChecker.create(PalindromeNumber::isPalindrome, PalindromeNumber::isPalindromeOptimal);

  @Test
  void isPalindrome() {
    checker.check(0, true)
      .check(121, true)
      .check(110, false)
      .check(-1, false)
      .check(123, false);
  }
}