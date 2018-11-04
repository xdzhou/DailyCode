package com.loic.leetcode.medium;

import com.loic.solution.SolutionChecker;
import org.junit.jupiter.api.Test;

import java.util.Arrays;


class GenerateParenthesesTest {

  @Test
  void testOne() {
    SolutionChecker.create(new GenerateParentheses())
      .check(1, Arrays.asList("()"));
  }

  @Test
  void testThree() {
    SolutionChecker.create(new GenerateParentheses())
      .check(3,
        Arrays.asList("((()))",
          "(()())",
          "(())()",
          "()(())",
          "()()()"));
  }

}