package com.loic.leetcode.medium;

import com.loic.solution.BiSolutionChecker;
import com.loic.solution.TestHelper;
import org.junit.jupiter.api.Test;

class RotatedSortedSearchTest {

  @Test
  void test() {
    BiSolutionChecker.create(new RotatedSortedSearch())
      .check(TestHelper.toIntArray(4, 5, 0, 1, 2, 3), 5, 1)
      .check(TestHelper.toIntArray(4, 5, 0, 1, 2, 3), 9, -1)
      .check(TestHelper.toIntArray(4, 5, 0, 1, 2, 3), 2, 4)
      .check(TestHelper.toIntArray(2, 3, 4, 5, 0, 1), 5, 3)
      .check(TestHelper.toIntArray(4, 5, 6, 7, 0, 1, 2), 0, 4)
      .check(TestHelper.toIntArray(4, 5, 6, 7, 0, 1, 2), 3, -1)
      .check(TestHelper.toIntArray(3, 1), 1, 1)
      .check(TestHelper.toIntArray(3, 4, 1), 1, 2);
  }
}