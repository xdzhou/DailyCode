package com.loic.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 54. Spiral Matrix
 * https://leetcode.com/problems/spiral-matrix/
 * <p>
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 */
public final class SpiralMatrix {

  public static List<Integer> spiralOrder(int[][] matrix) {
    if (matrix.length == 0) {
      return new ArrayList<>();
    }
    int minRow = 0, maxRow = matrix.length - 1;
    int minCol = 0, maxCol = matrix[0].length - 1;

    int curRow = 0, curCol = -1;
    int size = matrix.length * matrix[0].length;
    List<Integer> result = new ArrayList<>(size);

    while (result.size() < size) {
      if (curRow == minRow && curCol != maxCol) {
        // go to right
        if (curCol == minCol) {
          minCol++;
        }
        for (int col = curCol + 1; col <= maxCol; col++) {
          curCol = col;
          result.add(matrix[curRow][curCol]);
        }
      } else if (curCol == maxCol && curRow != maxRow) {
        // go to bottom
        if (curRow == minRow) {
          minRow++;
        }
        for (int row = curRow + 1; row <= maxRow; row++) {
          curRow = row;
          result.add(matrix[curRow][curCol]);
        }
      } else if (curRow == maxRow && curCol != minCol) {
        // got to left
        if (curCol == maxCol) {
          maxCol--;
        }
        for (int col = curCol - 1; col >= minCol; col--) {
          curCol = col;
          result.add(matrix[curRow][curCol]);
        }
      } else {
        // go to up
        if (curRow == maxRow) {
          maxRow--;
        }
        for (int row = curRow - 1; row >= minRow; row--) {
          curRow = row;
          result.add(matrix[curRow][curCol]);
        }
      }
    }

    return result;
  }
}
