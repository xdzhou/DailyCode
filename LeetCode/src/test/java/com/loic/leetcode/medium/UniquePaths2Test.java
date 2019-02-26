package com.loic.leetcode.medium;

import com.loic.leetcode.TestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UniquePaths2Test {

  @Test
  void find() {
    int[][] grid = new int[3][];
    grid[0] = TestHelper.toArray(0, 0, 0);
    grid[1] = TestHelper.toArray(0, 1, 0);
    grid[2] = TestHelper.toArray(0, 0, 0);
    Assertions.assertEquals(2, UniquePaths2.find(grid));
  }
}