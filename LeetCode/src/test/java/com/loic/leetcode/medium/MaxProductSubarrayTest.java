package com.loic.leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MaxProductSubarrayTest {

  @Test
  void simple() {
    Assertions.assertEquals(6, MaxProductSubarray.maxProduct(2, 3, -2, 4));
    Assertions.assertEquals(0, MaxProductSubarray.maxProduct(-2, 0, -1));

    Assertions.assertEquals(24, MaxProductSubarray.maxProduct(-2, 0, -1, 3, 4, -2));
  }

  @Test
  void failed() {
    Assertions.assertEquals(24, MaxProductSubarray.maxProduct(2, -5, -2, -4, 3));
  }
}