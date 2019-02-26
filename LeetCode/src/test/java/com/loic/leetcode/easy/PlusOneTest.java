package com.loic.leetcode.easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PlusOneTest {

  @Test
  void addOne() {
    arrayEqual(PlusOne.addOne(1, 2, 3), 1, 2, 4);
    arrayEqual(PlusOne.addOne(1, 0, 0), 1, 0, 1);
    arrayEqual(PlusOne.addOne(9, 9, 9), 1, 0, 0, 0);
  }

  private void arrayEqual(int[] result, int... expected) {
    Assertions.assertArrayEquals(expected, result);
  }
}