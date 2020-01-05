package com.loic.leetcode.medium;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DifferentWaysAddParenthesesTest {

  @Test
  void simple() {
    Assertions.assertEquals(Arrays.asList(1), DifferentWaysAddParentheses.diffWaysToCompute("1"));
    Assertions.assertEquals(Arrays.asList(3), DifferentWaysAddParentheses.diffWaysToCompute("1+2"));
    Assertions.assertEquals(Arrays.asList(6, 6), DifferentWaysAddParentheses.diffWaysToCompute("1+2+3"));
    Assertions.assertEquals(Arrays.asList(2, 0), DifferentWaysAddParentheses.diffWaysToCompute("2-1-1"));
    Assertions.assertEquals(Arrays.asList(-34, -10, -14, -10, 10), DifferentWaysAddParentheses.diffWaysToCompute("2*3-4*5"));
  }
}