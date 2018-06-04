package com.loic.leetcode;

import com.loic.solution.TestHelper;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class CombinationSumTest {
  private CombinationSum algo = new CombinationSum();

  @Test
  public void testEmpty() {
    Assert.assertTrue(algo.resolve(TestHelper.toIntArray(), 8).isEmpty());
    Assert.assertTrue(algo.resolve(TestHelper.toIntArray(9), 8).isEmpty());
    Assert.assertTrue(algo.resolve(TestHelper.toIntArray(3, 7), 8).isEmpty());
  }

  @Test
  public void testSimpleCase() {
    List<List<Integer>> result = Arrays.asList(Arrays.asList(7), Arrays.asList(2, 2, 3));
    Assert.assertEquals(result, algo.resolve(TestHelper.toIntArray(2, 3, 6, 7), 7));
  }
}