package com.loic.leetcode.medium;

import com.loic.leetcode.SolutionChecker;
import com.loic.leetcode.TestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NextPermutationTest {

  @Test
  void test() {
    SolutionChecker.<int[], Integer>create(nums -> {
      NextPermutation.resolve(nums);
      return 0;
    })
      .check(TestHelper.toArray(4, 5, 3, 1), (nums, o) -> {
        Assertions.assertArrayEquals(TestHelper.toArray(5, 1, 3, 4), nums);
      })
      .check(TestHelper.toArray(1), (nums, o) -> {
        Assertions.assertArrayEquals(TestHelper.toArray(1), nums);
      })
      .check(TestHelper.toArray(1, 2, 3), (nums, o) -> {
        Assertions.assertArrayEquals(TestHelper.toArray(1, 3, 2), nums);
      })
      .check(TestHelper.toArray(2, 1, 2, 2, 2, 2, 2, 1), (nums, o) -> {
        Assertions.assertArrayEquals(TestHelper.toArray(2, 2, 1, 1, 2, 2, 2, 2), nums);
      });
  }
}