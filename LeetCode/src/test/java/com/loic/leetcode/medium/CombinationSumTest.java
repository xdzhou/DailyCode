package com.loic.leetcode.medium;

import static com.loic.leetcode.TestHelper.toIntArray;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CombinationSumTest {

  @Test
  void testEmpty() {
    Assertions.assertTrue(CombinationSum.resolve(toIntArray(), 8).isEmpty());
    Assertions.assertTrue(CombinationSum.resolve(toIntArray(9), 8).isEmpty());
    Assertions.assertTrue(CombinationSum.resolve(toIntArray(3, 7), 8).isEmpty());
  }

  @Test
  void testSimpleCase() {
    List<List<Integer>> result = Arrays.asList(Arrays.asList(7), Arrays.asList(2, 2, 3));
    Assertions.assertEquals(result, CombinationSum.resolve(toIntArray(2, 3, 6, 7), 7));
  }
}