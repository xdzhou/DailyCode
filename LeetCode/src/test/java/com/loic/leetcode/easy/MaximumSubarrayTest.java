package com.loic.leetcode.easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MaximumSubarrayTest {

  @Test
  void negtive() {
    Assertions.assertEquals(-1, MaximumSubarray.maxSubArray(-1));
    Assertions.assertEquals(-1, MaximumSubarray.maxSubArray(-3, -4, -1));
  }

  @Test
  void leetcode() {
    Assertions.assertEquals(6, MaximumSubarray.maxSubArray(-2, 1, -3, 4, -1, 2, 1, -5, 4));
  }
}