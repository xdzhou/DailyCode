package com.loic.leetcode.hard;

/**
 * 85. Maximal Rectangle
 * https://leetcode.com/problems/maximal-rectangle/
 * <p>
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
 * <p>
 * Example:
 * <p>
 * Input:
 * [
 * ["1","0","1","0","0"],
 * ["1","0","1","1","1"],
 * ["1","1","1","1","1"],
 * ["1","0","0","1","0"]
 * ]
 * Output: 6
 */
public final class MaximalRectangle {

  public static int maxArea(char[][] matrix) {
    if (matrix.length == 0 || matrix[0].length == 0) {
      return 0;
    }
    int max = 0;
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        char c = matrix[i][j];
        if (c == '1' && j - 1 >= 0 && matrix[i][j - 1] != '0') {
          // transform the matrix to indicate how many '1' continuously
          matrix[i][j] = (char) (matrix[i][j - 1] + 1);
        }
        if (c != '0') {
          max = Math.max(max, maxAreaWithEnd(matrix, i, j));
        }
      }
    }
    // after this transformation
    // ["1","0","1","0","0"],
    // ["1","0","1","2","3"],
    // ["1","2","3","4","5"],
    // ["1","0","0","1","0"]

    return max;
  }

  /**
   * get the maximal rectangle whose bottom right point is (endX, endY)
   */
  private static int maxAreaWithEnd(char[][] matrix, int endX, int endY) {
    int rectangleHeight = 1;
    int rectangleWidth = Integer.MAX_VALUE;
    int topRightX = endX;
    int topRightY = endY;
    int max = 0;
    // compute the rectangle in height 1, 2, 3 ....
    while (topRightX >= 0 && matrix[topRightX][topRightY] != '0') {
      rectangleWidth = Math.min(rectangleWidth, matrix[topRightX][topRightY] - '0');
      max = Math.max(max, rectangleHeight * rectangleWidth);
      rectangleHeight++;
      topRightX--;
    }
    return max;
  }
}
