package com.loic.leetcode.hard;

import com.loic.solution.BiSolutionChecker;
import com.loic.solution.TestHelper;
import org.junit.jupiter.api.Test;

class TwoSortedArraysMedianTest {
  @Test
  void test() {
    BiSolutionChecker.create(new TwoSortedArraysMedian())
      .check(TestHelper.toIntArray(1), TestHelper.toIntArray(2), 1.5d)
      .check(TestHelper.toIntArray(1, 2, 3), TestHelper.toIntArray(2, 3), 2d)
      .check(TestHelper.toIntArray(1, 3), TestHelper.toIntArray(2), 2d)
      .check(TestHelper.toIntArray(1, 2), TestHelper.toIntArray(3, 4), 2.5d);
  }
}