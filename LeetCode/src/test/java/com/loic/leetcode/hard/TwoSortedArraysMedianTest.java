package com.loic.leetcode.hard;

import static com.loic.leetcode.TestHelper.toIntArray;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TwoSortedArraysMedianTest {
  @Test
  void test() {
    assertEquals(1.5d, TwoSortedArraysMedian.median(toIntArray(1), toIntArray(2)));
    assertEquals(2d, TwoSortedArraysMedian.median(toIntArray(1, 2, 3), toIntArray(2, 3)));
    assertEquals(2d, TwoSortedArraysMedian.median(toIntArray(1, 3), toIntArray(2)));
    assertEquals(2.5d, TwoSortedArraysMedian.median(toIntArray(1, 2), toIntArray(3, 4)));
  }

  @Test
  void odd() {
    assertEquals(3d, TwoSortedArraysMedian.median(toIntArray(1, 2), toIntArray(3, 4, 5)));
    assertEquals(3d, TwoSortedArraysMedian.median(toIntArray(1, 5), toIntArray(2, 3, 4)));
    assertEquals(3d, TwoSortedArraysMedian.median(toIntArray(1), toIntArray(2, 3, 4, 5)));
    assertEquals(3d, TwoSortedArraysMedian.median(toIntArray(), toIntArray(1, 2, 3, 4, 5)));
  }

  @Test
  void enev() {
    assertEquals(3.5d, TwoSortedArraysMedian.median(toIntArray(1, 2, 6), toIntArray(3, 4, 5)));
    assertEquals(3.5d, TwoSortedArraysMedian.median(toIntArray(1, 6), toIntArray(2, 3, 4, 5)));
    assertEquals(3.5d, TwoSortedArraysMedian.median(toIntArray(1), toIntArray(2, 3, 4, 5, 6)));
    assertEquals(3.5d, TwoSortedArraysMedian.median(toIntArray(1, 2, 3, 4, 5, 6), toIntArray()));
  }
}