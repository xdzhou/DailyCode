package com.loic.leetcode.medium;

import com.loic.leetcode.TestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ProductOfArrayExceptSelfTest {

  @Test
  void simple() {
    Assertions.assertArrayEquals(TestHelper.toArray(24, 12, 8, 6), ProductOfArrayExceptSelf.compute(1, 2, 3, 4));
    Assertions.assertArrayEquals(TestHelper.toArray(2, 1), ProductOfArrayExceptSelf.compute(1, 2));
  }
}