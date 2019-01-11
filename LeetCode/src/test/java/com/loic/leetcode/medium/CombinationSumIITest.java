package com.loic.leetcode.medium;

import static com.loic.leetcode.TestHelper.toArray;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CombinationSumIITest {

  @Test
  void testEmpty() {
    Assertions.assertTrue(CombinationSumII.resolve(toArray(), 8).isEmpty());
    Assertions.assertTrue(CombinationSumII.resolve(toArray(9), 8).isEmpty());
    Assertions.assertTrue(CombinationSumII.resolve(toArray(3, 7), 8).isEmpty());
    Assertions.assertTrue(CombinationSumII.resolve(toArray(1, 3, 7), 100).isEmpty());
  }

  @Test
  void testSimpleCase() {
    Assertions.assertEquals(Arrays.asList(Arrays.asList(7)), CombinationSumII.resolve(toArray(2, 3, 6, 7), 7));
  }

  @Test
  void testSmallCase() {
    List<List<Integer>> result = Arrays.asList(Arrays.asList(1, 7), Arrays.asList(2, 6), Arrays.asList(1, 1, 6), Arrays.asList(1, 2, 5));
    Assertions.assertEquals(result, CombinationSumII.resolve(toArray(10, 1, 2, 7, 6, 1, 5), 8));
  }
}