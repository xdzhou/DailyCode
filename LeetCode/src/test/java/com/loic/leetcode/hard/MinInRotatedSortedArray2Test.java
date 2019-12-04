package com.loic.leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MinInRotatedSortedArray2Test {

  @Test
  void simple() {
    Assertions.assertEquals(1, MinInRotatedSortedArray2.findMin(1, 3, 5));
    Assertions.assertEquals(0, MinInRotatedSortedArray2.findMin(2, 2, 2, 0, 1, 2));
  }

  @Test
  void failed() {
    Assertions.assertEquals(0, MinInRotatedSortedArray2.findMin(2, 2, 2, 0, 1));
    Assertions.assertEquals(1, MinInRotatedSortedArray2.findMin(3, 1, 1));
    Assertions.assertEquals(1, MinInRotatedSortedArray2.findMin(3, 1, 3));
  }
}