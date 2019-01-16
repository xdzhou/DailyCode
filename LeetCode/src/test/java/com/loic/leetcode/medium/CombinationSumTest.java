package com.loic.leetcode.medium;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CombinationSumTest {

  @Test
  void testEmpty() {
    Assertions.assertTrue(CombinationSum.combine(8).isEmpty());
    Assertions.assertTrue(CombinationSum.combine(8, 9).isEmpty());
    Assertions.assertTrue(CombinationSum.combine(8, 7, 3).isEmpty());
  }

  @Test
  void testSimpleCase() {
    List<List<Integer>> result = Arrays.asList(Arrays.asList(2, 2, 3), Arrays.asList(2, 5), Arrays.asList(7));
    Assertions.assertEquals(result, CombinationSum.combine(7, 2, 3, 5, 7));
  }
}