package com.loic.leetcode.medium;

import com.loic.leetcode.TestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Search2DMatrix2Test {

  @Test
  void simple() {
    int[][] matrix = new int[5][];
    matrix[0] = TestHelper.toArray(1, 4, 7, 11, 15);
    matrix[1] = TestHelper.toArray(2, 5, 8, 12, 19);
    matrix[2] = TestHelper.toArray(3, 6, 9, 16, 22);
    matrix[3] = TestHelper.toArray(10, 13, 14, 17, 24);
    matrix[4] = TestHelper.toArray(18, 21, 23, 26, 30);

    Assertions.assertFalse(Search2DMatrix2.searchMatrix(matrix, 0));
    Assertions.assertFalse(Search2DMatrix2.searchMatrix(matrix, 33));
    Assertions.assertFalse(Search2DMatrix2.searchMatrix(matrix, 20));

    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        Assertions.assertTrue(Search2DMatrix2.searchMatrix(matrix, matrix[i][j]));
      }
    }
  }

  @Test
  void failed() {
    int[][] matrix = new int[][]{{48, 65, 70, 113, 133, 163, 170, 216, 298, 389}, {
      89, 169, 215, 222, 250, 348, 379, 426, 469, 554}, {178, 202, 253, 294, 367, 392, 428, 434, 499, 651}, {
      257, 276, 284, 332, 380, 470, 516, 561, 657, 698}, {275, 331, 391, 432, 500, 595, 602, 673, 758, 783}, {
      357, 365, 412, 450, 556, 642, 690, 752, 801, 887}, {359, 451, 534, 609, 654, 662, 693, 766, 803, 964}, {
      390, 484, 614, 669, 684, 711, 767, 804, 857, 1055}, {400, 515, 683, 732, 812, 834, 880, 930, 1012, 1130}, {
      480, 538, 695, 751, 864, 939, 966, 1027, 1089, 1224}};

    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        Assertions.assertTrue(Search2DMatrix2.searchMatrix(matrix, matrix[i][j]));
      }
    }
  }
}