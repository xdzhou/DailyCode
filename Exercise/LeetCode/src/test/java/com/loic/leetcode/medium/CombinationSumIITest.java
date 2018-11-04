package com.loic.leetcode.medium;


import com.loic.solution.TestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class CombinationSumIITest {
  private CombinationSumII algo = new CombinationSumII();

  @Test
  void testEmpty() {
    Assertions.assertTrue(algo.resolve(TestHelper.toIntArray(), 8).isEmpty());
    Assertions.assertTrue(algo.resolve(TestHelper.toIntArray(9), 8).isEmpty());
    Assertions.assertTrue(algo.resolve(TestHelper.toIntArray(3, 7), 8).isEmpty());
    Assertions.assertTrue(algo.resolve(TestHelper.toIntArray(1, 3, 7), 100).isEmpty());
  }

  @Test
  void testSimpleCase() {
    Assertions.assertEquals(Arrays.asList(Arrays.asList(7)), algo.resolve(TestHelper.toIntArray(2, 3, 6, 7), 7));
  }

  @Test
  void testSmallCase() {
    List<List<Integer>> result = Arrays.asList(Arrays.asList(1, 7), Arrays.asList(2, 6), Arrays.asList(1, 1, 6), Arrays.asList(1, 2, 5));
    Assertions.assertEquals(result, algo.resolve(TestHelper.toIntArray(10, 1, 2, 7, 6, 1, 5), 8));
  }
}