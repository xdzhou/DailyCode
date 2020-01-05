package com.loic.leetcode.medium;

import com.loic.leetcode.TestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SingleNumber3Test {

  @Test
  void simple() {
    Assertions.assertArrayEquals(TestHelper.toArray(5, 3), SingleNumber3.singleNumber(1, 2, 1, 3, 2, 5));
  }
}