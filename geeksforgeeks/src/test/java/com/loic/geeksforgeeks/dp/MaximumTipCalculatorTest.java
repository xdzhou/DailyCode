package com.loic.geeksforgeeks.dp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MaximumTipCalculatorTest {

  @Test
  void simpleTest() {
    Assertions.assertEquals(21, MaximumTipCalculator.resolve(5, 3, 3, new int[]{1, 2, 3, 4, 5}, new int[]{5, 4, 3, 2, 1}));
  }

  @Test
  void mediumTest() {
    Assertions.assertEquals(43, MaximumTipCalculator.resolve(8, 4, 4, new int[]{1, 4, 3, 2, 7, 5, 9, 6}, new int[]{1, 2, 3, 6, 5, 4, 9, 8}));
    Assertions.assertEquals(160, MaximumTipCalculator.resolve(4, 4, 1, new int[]{10, 20, 30, 40}, new int[]{1, 25, 3, 100}));

  }
}