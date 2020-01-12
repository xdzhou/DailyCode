package com.loic.leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HIndexTest {

  @Test
  void hIndex() {
    Assertions.assertEquals(0, HIndex.hIndex());
    Assertions.assertEquals(1, HIndex.hIndex(1));
    Assertions.assertEquals(2, HIndex.hIndex(2, 3));
    Assertions.assertEquals(1, HIndex.hIndex(2, 1));
    Assertions.assertEquals(0, HIndex.hIndex(0, 0));
    Assertions.assertEquals(3, HIndex.hIndex(3, 0, 6, 1, 5));
  }
}