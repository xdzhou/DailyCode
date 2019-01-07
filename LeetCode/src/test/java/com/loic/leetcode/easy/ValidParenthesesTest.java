package com.loic.leetcode.easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ValidParenthesesTest {

  @Test
  void isValid() {
    Assertions.assertTrue(ValidParentheses.isValid("()"));
    Assertions.assertTrue(ValidParentheses.isValid("([]{})"));
    Assertions.assertTrue(ValidParentheses.isValid("([]{})"));
    Assertions.assertFalse(ValidParentheses.isValid("([]{)"));
    Assertions.assertFalse(ValidParentheses.isValid("([]{"));
    Assertions.assertFalse(ValidParentheses.isValid("]"));
  }
}