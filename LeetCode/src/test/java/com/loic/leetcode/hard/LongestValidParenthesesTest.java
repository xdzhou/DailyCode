package com.loic.leetcode.hard;

import com.loic.leetcode.SolutionChecker;
import org.junit.jupiter.api.Test;

class LongestValidParenthesesTest {
  private final SolutionChecker<String, Integer> checker =
    SolutionChecker.create(LongestValidParentheses::dpResolve, LongestValidParentheses::stackResolve, LongestValidParentheses::resolve);

  @Test
  void find() {
    checker.check("", 0)
      .check("(", 0)
      .check(")", 0)
      .check("()()()", 6)
      .check(")))((", 0)
      .check("(((()()", 4)
      .check("(()(())())", 10);
  }
}