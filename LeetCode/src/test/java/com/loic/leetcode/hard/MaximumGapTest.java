package com.loic.leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MaximumGapTest {

  @Test
  void simple() {
    Assertions.assertEquals(3, MaximumGap.maxGap(3, 6, 9, 1));
    Assertions.assertEquals(0, MaximumGap.maxGap(3));
    Assertions.assertEquals(2, MaximumGap.maxGap(3, 1));
    Assertions.assertEquals(0, MaximumGap.maxGap(3, 3, 3, 3));
    Assertions.assertEquals(1, MaximumGap.maxGap(3, 3, 4));
  }
}