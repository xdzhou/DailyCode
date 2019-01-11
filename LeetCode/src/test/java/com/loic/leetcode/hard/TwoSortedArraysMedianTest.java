package com.loic.leetcode.hard;

import static com.loic.leetcode.TestHelper.toArray;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TwoSortedArraysMedianTest {
  @Test
  void test() {
    assertEquals(1.5d, TwoSortedArraysMedian.median(toArray(1), toArray(2)));
    assertEquals(2d, TwoSortedArraysMedian.median(toArray(1, 2, 3), toArray(2, 3)));
    assertEquals(2d, TwoSortedArraysMedian.median(toArray(1, 3), toArray(2)));
    assertEquals(2.5d, TwoSortedArraysMedian.median(toArray(1, 2), toArray(3, 4)));
  }

  @Test
  void odd() {
    assertEquals(3d, TwoSortedArraysMedian.median(toArray(1, 2), toArray(3, 4, 5)));
    assertEquals(3d, TwoSortedArraysMedian.median(toArray(1, 5), toArray(2, 3, 4)));
    assertEquals(3d, TwoSortedArraysMedian.median(toArray(1), toArray(2, 3, 4, 5)));
    assertEquals(3d, TwoSortedArraysMedian.median(toArray(), toArray(1, 2, 3, 4, 5)));
  }

  @Test
  void enev() {
    assertEquals(3.5d, TwoSortedArraysMedian.median(toArray(1, 2, 6), toArray(3, 4, 5)));
    assertEquals(3.5d, TwoSortedArraysMedian.median(toArray(1, 6), toArray(2, 3, 4, 5)));
    assertEquals(3.5d, TwoSortedArraysMedian.median(toArray(1), toArray(2, 3, 4, 5, 6)));
    assertEquals(3.5d, TwoSortedArraysMedian.median(toArray(1, 2, 3, 4, 5, 6), toArray()));
  }
}