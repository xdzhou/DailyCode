package com.loic.leetcode.medium;

/**
 * 221. Maximal Square
 * <p>
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 * <p>
 * Example:
 * <p>
 * Input:
 * <p>
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * <p>
 * Output: 4
 */
public class MaximalSquare {

  public static int maximalSquare(char[][] matrix) {
    if (matrix.length == 0 || matrix[0].length == 0) {
      return 0;
    }
    int[] preDp = new int[matrix[0].length];
    int[] curDp = new int[matrix[0].length];
    int maxLen = 0;
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < preDp.length; j++) {
        int num = matrix[i][j] - '0';
        if (i == 0 || j == 0 || num == 0) {
          curDp[j] = num;
        } else {
          int min = Math.min(preDp[j], preDp[j - 1]);
          min = Math.min(min, curDp[j - 1]);
          curDp[j] = 1 + min;
        }
        maxLen = Math.max(maxLen, curDp[j]);
      }
      int[] tmp = preDp;
      preDp = curDp;
      curDp = tmp;
    }
    return maxLen * maxLen;
  }
}
