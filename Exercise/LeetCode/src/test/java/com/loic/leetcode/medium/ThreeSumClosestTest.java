package com.loic.leetcode.medium;

import com.loic.solution.BiSolutionChecker;
import com.loic.solution.TestHelper;
import org.junit.jupiter.api.Test;

class ThreeSumClosestTest {

  @Test
  void test() {
    BiSolutionChecker.create(new ThreeSumClosest())
      .check(TestHelper.toIntArray(-1, 2, 1, -4), 1, 2)
      .check(TestHelper.toIntArray(1, 1, 1, 0), -100, 2);
  }

}