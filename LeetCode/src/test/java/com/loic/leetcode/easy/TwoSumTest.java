package com.loic.leetcode.easy;

import static com.loic.leetcode.TestHelper.toArray;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class TwoSumTest {

  @Test
  void test() {
    assertArrayEquals(toArray(0, 1), TwoSum.findTarget(toArray(2, 7, 11, 15), 9));
  }
}