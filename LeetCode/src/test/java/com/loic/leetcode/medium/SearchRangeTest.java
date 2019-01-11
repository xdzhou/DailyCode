package com.loic.leetcode.medium;

import static com.loic.leetcode.TestHelper.toArray;

import org.junit.jupiter.api.Assertions;

class SearchRangeTest {

  //FIXME
  //@Test
  void test() {
    testCase(toArray(0, 1, 2, 2, 3), 2, toArray(2, 3));
    testCase(toArray(0, 1, 2, 3), 2, toArray(2, 2));
    testCase(toArray(0), 0, toArray(0, 0));
    testCase(toArray(0, 1), 1, toArray(1, 1));
    testCase(toArray(0, 1, 2, 3, 4), 5, toArray(-1, -1));
    testCase(toArray(2, 2, 2, 2, 2), 2, toArray(0, 4));
  }

  private void testCase(int[] nums, int target, int[] expected) {
    Assertions.assertArrayEquals(expected, SearchRange.resolve(nums, target));
  }
}