package com.loic.leetcode.medium;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SpiralMatrixTest {

  @Test
  void empty() {
    Assertions.assertTrue(SpiralMatrix.spiralOrder(new int[0][0]).isEmpty());
  }

  @Test
  void simple() {
    int[][] matrix = new int[1][1];
    matrix[0][0] = 1;
    Assertions.assertEquals(Collections.singletonList(1), SpiralMatrix.spiralOrder(matrix));
  }

  @Test
  void normal() {
    int[][] matrix = new int[2][2];
    matrix[0][0] = 1;
    matrix[0][1] = 2;
    matrix[1][0] = 3;
    matrix[1][1] = 4;
    Assertions.assertEquals(Arrays.asList(1, 2, 4, 3), SpiralMatrix.spiralOrder(matrix));
  }
}