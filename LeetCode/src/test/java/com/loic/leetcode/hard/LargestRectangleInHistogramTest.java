package com.loic.leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LargestRectangleInHistogramTest {

  @Test
  void area() {
    Assertions.assertEquals(0, LargestRectangleInHistogram.area());
    Assertions.assertEquals(9, LargestRectangleInHistogram.area(9));
    Assertions.assertEquals(10, LargestRectangleInHistogram.area(2, 1, 5, 6, 2, 3));
  }
}