package com.loic.leetcode.medium;

import static com.loic.leetcode.TestHelper.toIntArray;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RotatedSortedSearchTest {

  @Test
  void test() {
    testCase(toIntArray(4, 5, 0, 1, 2, 3), 5, 1);
    testCase(toIntArray(4, 5, 0, 1, 2, 3), 9, -1);
    testCase(toIntArray(4, 5, 0, 1, 2, 3), 2, 4);
    testCase(toIntArray(2, 3, 4, 5, 0, 1), 5, 3);
    testCase(toIntArray(4, 5, 6, 7, 0, 1, 2), 0, 4);
    testCase(toIntArray(4, 5, 6, 7, 0, 1, 2), 3, -1);
    testCase(toIntArray(3, 1), 1, 1);
    testCase(toIntArray(3, 4, 1), 1, 2);
  }

  private void testCase(int[] nums, int key, int expected) {
    Assertions.assertEquals(expected, RotatedSortedSearch.resolve(nums, key));
    Assertions.assertEquals(expected, RotatedSortedSearch.search(nums, key));
  }
}