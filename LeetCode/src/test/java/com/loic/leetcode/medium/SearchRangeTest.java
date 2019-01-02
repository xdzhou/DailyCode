package com.loic.leetcode.medium;

import static com.loic.leetcode.TestHelper.toIntArray;

import org.junit.jupiter.api.Assertions;

class SearchRangeTest {

  //FIXME
  //@Test
  void test() {
    testCase(toIntArray(0, 1, 2, 2, 3), 2, toIntArray(2, 3));
    testCase(toIntArray(0, 1, 2, 3), 2, toIntArray(2, 2));
    testCase(toIntArray(0), 0, toIntArray(0, 0));
    testCase(toIntArray(0, 1), 1, toIntArray(1, 1));
    testCase(toIntArray(0, 1, 2, 3, 4), 5, toIntArray(-1, -1));
    testCase(toIntArray(2, 2, 2, 2, 2), 2, toIntArray(0, 4));
  }

  private void testCase(int[] nums, int target, int[] expected) {
    Assertions.assertArrayEquals(expected, SearchRange.resolve(nums, target));
  }
}