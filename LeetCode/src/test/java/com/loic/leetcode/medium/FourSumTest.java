package com.loic.leetcode.medium;

import static com.loic.leetcode.TestHelper.toIntArray;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FourSumTest {

  @Test
  void test() {
    List<List<Integer>> expected = Arrays.asList(Arrays.asList(-2, -1, 1, 2), Arrays.asList(-2, 0, 0, 2), Arrays.asList(-1, 0, 0, 1));
    Assertions.assertEquals(expected, FourSum.resolve(toIntArray(1, 0, -1, 0, -2, 2), 0));
  }
}