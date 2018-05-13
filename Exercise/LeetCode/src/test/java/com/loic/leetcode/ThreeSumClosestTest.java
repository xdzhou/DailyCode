package com.loic.leetcode;

import com.loic.solution.BiSolutionChecker;
import com.loic.solution.TestHelper;
import org.junit.Test;

public class ThreeSumClosestTest {

  @Test
  public void test() {
    BiSolutionChecker.create(new ThreeSumClosest())
      .check(TestHelper.toIntArray(-1, 2, 1, -4), 1, 2)
      .check(TestHelper.toIntArray(1, 1, 1, 0), -100, 2);
  }

}