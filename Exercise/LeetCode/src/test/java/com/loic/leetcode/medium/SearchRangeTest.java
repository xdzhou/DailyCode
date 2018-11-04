package com.loic.leetcode.medium;

import com.loic.solution.BiSolutionChecker;
import com.loic.solution.TestHelper;

class SearchRangeTest {

  //FIXME
  //@Test
  void test() {
    BiSolutionChecker.create(new SearchRange())
      .check(TestHelper.toIntArray(0, 1, 2, 2, 3), 2, TestHelper.toIntArray(2, 3))
      .check(TestHelper.toIntArray(0, 1, 2, 3), 2, TestHelper.toIntArray(2, 2))
      .check(TestHelper.toIntArray(0), 0, TestHelper.toIntArray(0, 0))
      .check(TestHelper.toIntArray(0, 1), 1, TestHelper.toIntArray(1, 1))
      .check(TestHelper.toIntArray(0, 1, 2, 3, 4), 5, TestHelper.toIntArray(-1, -1))
      .check(TestHelper.toIntArray(2, 2, 2, 2, 2), 2, TestHelper.toIntArray(0, 4));
  }
}