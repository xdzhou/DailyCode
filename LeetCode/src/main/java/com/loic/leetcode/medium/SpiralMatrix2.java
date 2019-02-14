package com.loic.leetcode.medium;

/**
 * 59. Spiral Matrix II
 * https://leetcode.com/problems/spiral-matrix-ii/
 * <p>
 * Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
 */
public final class SpiralMatrix2 {

  public static int[][] generateMatrix(int n) {
    int[][] matrix = new int[n][n];
    int topRow = 0, downRow = n - 1, leftCol = -1, rightCol = n - 1;

    int num = 1;
    int direction = 0;
    while (num <= n * n) {
      if (direction % 4 == 0) {
        // go right
        leftCol++;
        for (int col = leftCol; col <= rightCol; col++) {
          matrix[topRow][col] = num++;
        }
      } else if (direction % 4 == 1) {
        // go down
        topRow++;
        for (int row = topRow; row <= downRow; row++) {
          matrix[row][rightCol] = num++;
        }
      } else if (direction % 4 == 2) {
        // go left
        rightCol--;
        for (int col = rightCol; col >= leftCol; col--) {
          matrix[downRow][col] = num++;
        }
      } else {
        // go up
        downRow--;
        for (int row = downRow; row >= topRow; row--) {
          matrix[row][leftCol] = num++;
        }
      }
      direction++;
    }

    return matrix;
  }
}
