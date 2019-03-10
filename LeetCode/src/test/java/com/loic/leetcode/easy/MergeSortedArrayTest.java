package com.loic.leetcode.easy;

import static com.loic.leetcode.TestHelper.toArray;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MergeSortedArrayTest {

  @Test
  void merge() {
    int[] nums = toArray(1, 2, 3, 0, 0, 0);
    MergeSortedArray.merge(nums, 3, toArray(2, 5, 6), 3);
    Assertions.assertArrayEquals(toArray(1, 2, 2, 3, 5, 6), nums);
  }

  @Test
  void leftPartSmaller() {
    int[] nums = toArray(1, 2, 3, 0, 0, 0);
    MergeSortedArray.merge(nums, 3, toArray(4, 5, 6), 3);
    Assertions.assertArrayEquals(toArray(1, 2, 3, 4, 5, 6), nums);
  }

  @Test
  void rightPartSmaller() {
    int[] nums = toArray(4, 5, 6, 0, 0, 0);
    MergeSortedArray.merge(nums, 3, toArray(1, 2, 3), 3);
    Assertions.assertArrayEquals(toArray(1, 2, 3, 4, 5, 6), nums);
  }
}