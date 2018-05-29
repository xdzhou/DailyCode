package com.loic.leetcode;

import com.loic.solution.SolutionChecker;
import com.loic.solution.TestHelper;
import org.junit.Assert;
import org.junit.Test;

public class NextPermutationTest {

  @Test
  public void test() {
    SolutionChecker.create(new NextPermutation())
      .check(TestHelper.toIntArray(4, 5, 3, 1), (nums, o) -> {
        Assert.assertArrayEquals(TestHelper.toIntArray(5, 1, 3, 4), nums);
      })
      .check(TestHelper.toIntArray(1), (nums, o) -> {
        Assert.assertArrayEquals(TestHelper.toIntArray(1), nums);
      })
      .check(TestHelper.toIntArray(1, 2, 3), (nums, o) -> {
        Assert.assertArrayEquals(TestHelper.toIntArray(1, 3, 2), nums);
      })
      .check(TestHelper.toIntArray(2, 1, 2, 2, 2, 2, 2, 1), (nums, o) -> {
        Assert.assertArrayEquals(TestHelper.toIntArray(2, 2, 1, 1, 2, 2, 2, 2), nums);
      });
  }
}