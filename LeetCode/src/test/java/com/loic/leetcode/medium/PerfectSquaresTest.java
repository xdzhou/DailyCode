package com.loic.leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PerfectSquaresTest {

  @Test
  void numSquares() {
    Assertions.assertEquals(3, PerfectSquares.numSquares(12));
    Assertions.assertEquals(2, PerfectSquares.numSquares(13));
  }
}