package com.loic.leetcode.medium;

import com.loic.leetcode.TestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Search2DMatrixTest {

  @Test
  void search() {
    int[][] matrix = new int[3][];
    matrix[0] = TestHelper.toArray(1, 3, 5, 7);
    matrix[1] = TestHelper.toArray(10, 11, 16, 20);
    matrix[2] = TestHelper.toArray(23, 30, 34, 50);
    Assertions.assertTrue(Search2DMatrix.search(matrix, 1));
    Assertions.assertTrue(Search2DMatrix.search(matrix, 3));
    Assertions.assertTrue(Search2DMatrix.search(matrix, 7));
    Assertions.assertTrue(Search2DMatrix.search(matrix, 23));

    Assertions.assertFalse(Search2DMatrix.search(matrix, 51));
    Assertions.assertFalse(Search2DMatrix.search(matrix, -23));
    Assertions.assertFalse(Search2DMatrix.search(matrix, 8));
  }
}