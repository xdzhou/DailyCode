package com.loic.leetcode.medium;

import java.util.Arrays;

import com.loic.leetcode.SolutionChecker;
import org.junit.jupiter.api.Test;


class GenerateParenthesesTest {

  @Test
  void testOne() {
    SolutionChecker.create(GenerateParentheses::resolve)
      .check(1, Arrays.asList("()"));
  }

  @Test
  void testThree() {
    SolutionChecker.create(GenerateParentheses::resolve)
      .check(3,
        Arrays.asList("((()))",
          "(()())",
          "(())()",
          "()(())",
          "()()()"));
  }

}