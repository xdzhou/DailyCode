package com.loic.leetcode.medium;

import java.util.Arrays;

/**
 * 74. Search a 2D Matrix
 * https://leetcode.com/problems/search-a-2d-matrix/
 * <p>
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 */
public final class Search2DMatrix {

  public static boolean search(int[][] matrix, int target) {
    int rowNum = matrix.length;
    int colNum = rowNum > 0 ? matrix[0].length : 0;
    if (rowNum > 0 && colNum > 0) {
      //binary search in first column
      int from = 0, to = matrix.length - 1;
      while (from <= to) {
        int mid = (from + to) >>> 1;
        if (matrix[mid][0] == target) {
          return true;
        } else if (matrix[mid][0] < target) {
          from = mid + 1;
        } else {
          to = mid - 1;
        }
      }
      // now 'from' is the insert index of target
      // target may be found in line 'from-1'
      if (from - 1 >= 0) {
        return Arrays.binarySearch(matrix[from - 1], target) >= 0;
      }
    }
    return false;
  }
}
