package com.loic.leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SpiralMatrix2Test {

  @Test
  void generateMatrix() {
    int[][] computed = SpiralMatrix2.generateMatrix(3);
    int[][] expected = matrix(1, 2, 3, 8, 9, 4, 7, 6, 5);
    for (int i = 0; i < 3; i++) {
      Assertions.assertArrayEquals(computed[i], expected[i]);
    }
  }

  private int[][] matrix(int... nums) {
    int n = (int) Math.sqrt(nums.length);
    int[][] matrix = new int[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        matrix[i][j] = nums[i * n + j];
      }
    }
    return matrix;
  }
}