package com.loic.leetcode.medium;

import com.loic.leetcode.TestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MinimumPathSumTest {

  @Test
  void minSum() {
    int[][] matrix = new int[1][];
    matrix[0] = TestHelper.toArray(3, 1, 1);

    Assertions.assertEquals(5, MinimumPathSum.minSum(matrix));
  }
}