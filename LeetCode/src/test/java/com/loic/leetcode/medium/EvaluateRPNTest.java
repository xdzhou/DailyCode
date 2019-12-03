package com.loic.leetcode.medium;

import static com.loic.leetcode.medium.EvaluateRPN.evalRPN;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EvaluateRPNTest {

  @Test
  public void simple() {
    Assertions.assertEquals(9, evalRPN("2", "1", "+", "3", "*"));
    Assertions.assertEquals(22,
      evalRPN("10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"));
  }
}