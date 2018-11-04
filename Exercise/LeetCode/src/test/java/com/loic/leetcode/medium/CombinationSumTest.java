package com.loic.leetcode.medium;

import com.loic.solution.TestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class CombinationSumTest {
  private CombinationSum algo = new CombinationSum();

  @Test
  void testEmpty() {
    Assertions.assertTrue(algo.resolve(TestHelper.toIntArray(), 8).isEmpty());
    Assertions.assertTrue(algo.resolve(TestHelper.toIntArray(9), 8).isEmpty());
    Assertions.assertTrue(algo.resolve(TestHelper.toIntArray(3, 7), 8).isEmpty());
  }

  @Test
  void testSimpleCase() {
    List<List<Integer>> result = Arrays.asList(Arrays.asList(7), Arrays.asList(2, 2, 3));
    Assertions.assertEquals(result, algo.resolve(TestHelper.toIntArray(2, 3, 6, 7), 7));
  }
}