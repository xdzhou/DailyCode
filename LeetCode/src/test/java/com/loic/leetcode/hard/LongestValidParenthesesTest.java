package com.loic.leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LongestValidParenthesesTest {

  @Test
  void find() {
    Assertions.assertEquals(0, LongestValidParentheses.find(""));
    Assertions.assertEquals(0, LongestValidParentheses.find("("));
    Assertions.assertEquals(0, LongestValidParentheses.find(")"));
    Assertions.assertEquals(6, LongestValidParentheses.find("()()()"));
    Assertions.assertEquals(0, LongestValidParentheses.find(")))(("));
    Assertions.assertEquals(4, LongestValidParentheses.find("(((()()"));
    Assertions.assertEquals(10, LongestValidParentheses.find("(()(())())"));
  }
}