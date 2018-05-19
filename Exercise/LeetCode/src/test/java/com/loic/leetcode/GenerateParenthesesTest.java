package com.loic.leetcode;

import com.loic.solution.SolutionChecker;
import org.junit.Test;

import java.util.Arrays;


public class GenerateParenthesesTest {

  @Test
  public void testOne() {
    SolutionChecker.create(new GenerateParentheses())
      .check(1, Arrays.asList("()"));
  }

  @Test
  public void testThree() {
    SolutionChecker.create(new GenerateParentheses())
      .check(3,
        Arrays.asList("((()))",
          "(()())",
          "(())()",
          "()(())",
          "()()()"));
  }

}