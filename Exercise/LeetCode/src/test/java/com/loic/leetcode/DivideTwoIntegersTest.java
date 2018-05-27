package com.loic.leetcode;

import com.loic.solution.BiSolutionChecker;
import org.junit.Test;

public class DivideTwoIntegersTest {

  @Test
  public void test() {
    BiSolutionChecker.create(new DivideTwoIntegers())
      .check(10, 3, 3)
      .check(9, 3, 3)
      .check(9, 1, 9)
      .check(9, -1, -9)
      .check(-9, 3, -3)
      .check(-1010369383, -2147483648, 0)
      .check(7, -3, -2)
      .check(Integer.MIN_VALUE, -1, Integer.MAX_VALUE);
  }
}