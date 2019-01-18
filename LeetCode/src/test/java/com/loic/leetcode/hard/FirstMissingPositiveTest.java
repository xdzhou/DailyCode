package com.loic.leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FirstMissingPositiveTest {

  @Test
  void find() {
    Assertions.assertEquals(1, FirstMissingPositive.find());
    Assertions.assertEquals(1, FirstMissingPositive.find(2, 0, -1, 3));
    Assertions.assertEquals(2, FirstMissingPositive.find(1, 0, -1, 3));
    Assertions.assertEquals(5, FirstMissingPositive.find(1, 2, 4, 0, -1, 3));

    Assertions.assertEquals(1, FirstMissingPositive.optimalFind());
    Assertions.assertEquals(2, FirstMissingPositive.optimalFind(1, 1));
    Assertions.assertEquals(1, FirstMissingPositive.optimalFind(2, 0, -1, 3));
    Assertions.assertEquals(2, FirstMissingPositive.optimalFind(1, 0, -1, 3));
    Assertions.assertEquals(5, FirstMissingPositive.optimalFind(1, 2, 4, 0, -1, 3));
  }
}