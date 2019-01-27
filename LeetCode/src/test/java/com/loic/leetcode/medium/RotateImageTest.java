package com.loic.leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RotateImageTest {

  @Test
  void rotate() {
    test(1);
    test(1, 2, 3, 4);
    test(1, 2, 3, 4, 5, 6, 7, 8, 9);
    test(1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6);
  }

  private void test(int... nums) {
    int N = (int) Math.sqrt(nums.length);
    int[][] matrix = new int[N][N];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        matrix[i][j] = nums[i * N + j];
      }
    }
    RotateImage.rotate(matrix);
    RotateImage.rotate(matrix);
    RotateImage.rotate(matrix);
    RotateImage.rotate(matrix);
    int[] result = new int[nums.length];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        result[i * N + j] = matrix[i][j];
      }
    }
    Assertions.assertArrayEquals(nums, result);
  }
}