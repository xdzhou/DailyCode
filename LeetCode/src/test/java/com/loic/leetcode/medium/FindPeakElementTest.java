package com.loic.leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FindPeakElementTest {

  @Test
  void peakIndex() {
    Assertions.assertEquals(2, FindPeakElement.peakIndex(1, 2, 3, 1));
    Assertions.assertEquals(5, FindPeakElement.peakIndex(1, 2, 1, 3, 5, 6, 4));
  }

  @Test
  void failed() {
    Assertions.assertEquals(1, FindPeakElement.peakIndex(-2147483648, -2147483647));
  }
}