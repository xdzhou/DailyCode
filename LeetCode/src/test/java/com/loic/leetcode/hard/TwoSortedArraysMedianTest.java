package com.loic.leetcode.hard;

import static com.loic.leetcode.TestHelper.toIntArray;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TwoSortedArraysMedianTest {
  @Test
  void test() {
    assertEquals(1.5d, TwoSortedArraysMedian.resolve(toIntArray(1), toIntArray(2)));
    assertEquals(2d, TwoSortedArraysMedian.resolve(toIntArray(1, 2, 3), toIntArray(2, 3)));
    assertEquals(2d, TwoSortedArraysMedian.resolve(toIntArray(1, 3), toIntArray(2)));
    assertEquals(2.5d, TwoSortedArraysMedian.resolve(toIntArray(1, 2), toIntArray(3, 4)));
  }
}