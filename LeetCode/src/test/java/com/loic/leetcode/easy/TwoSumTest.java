package com.loic.leetcode.easy;

import static com.loic.leetcode.TestHelper.toIntArray;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class TwoSumTest {

  @Test
  void test() {
    assertArrayEquals(toIntArray(0, 1), TwoSum.resolve(toIntArray(2, 7, 11, 15), 9));
  }
}