package com.loic.leetcode.medium;

import java.util.Arrays;

/**
 * 240. Search a 2D Matrix II
 * <p>
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 * <p>
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 * Example:
 * <p>
 * Consider the following matrix:
 * <p>
 * [
 * [1,   4,  7, 11, 15],
 * [2,   5,  8, 12, 19],
 * [3,   6,  9, 16, 22],
 * [10, 13, 14, 17, 24],
 * [18, 21, 23, 26, 30]
 * ]
 * Given target = 5, return true.
 * <p>
 * Given target = 20, return false.
 */
public class Search2DMatrix2 {

  public static boolean searchMatrix(int[][] matrix, int target) {
    if (matrix.length == 0 || matrix[0].length == 0) {
      return false;
    }
    return searchMatrix(matrix, target, 0, 0, matrix.length - 1, matrix[0].length - 1);
  }

  private static boolean searchMatrix(int[][] matrix, int target, int topRow, int topCol, int downRow, int downCol) {
    while (topRow <= downRow && topCol <= downCol) {
      if (target < matrix[topRow][topCol] || target > matrix[downRow][downCol]) {
        return false;
      } else if (target == matrix[topRow][topCol] || target == matrix[downRow][downCol]) {
        return true;
      }
      int index = Arrays.binarySearch(matrix[topRow], topCol, downCol + 1, target);
      if (index >= 0) {
        return true;
      }
      int nextDownCol = -index - 1 - 1;
      if (downCol == nextDownCol) {
        topRow++;
      }
      downCol = nextDownCol;

      index = Arrays.binarySearch(matrix[downRow], topCol, downCol + 1, target);
      if (index >= 0) {
        return true;
      }
      int nextTopCol = -index - 1;
      if (topCol == nextTopCol) {
        downRow--;
      }
      topCol = nextTopCol;
    }
    return false;
  }
}
