package com.loic.leetcode.hard;

import com.loic.leetcode.TestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MaximalRectangleTest {

  @Test
  void maxArea() {
    char[][] matrix = new char[4][];
    matrix[0] = TestHelper.toCharArray('1', '0', '1', '0', '0');
    matrix[1] = TestHelper.toCharArray('1', '0', '1', '1', '1');
    matrix[2] = TestHelper.toCharArray('1', '1', '1', '1', '1');
    matrix[3] = TestHelper.toCharArray('1', '0', '0', '1', '0');

    Assertions.assertEquals(6, MaximalRectangle.maxArea(matrix));
  }

  @Test
  void failed(){
    char[][] matrix = new char[1][];
    matrix[0] = TestHelper.toCharArray('1');

    Assertions.assertEquals(1, MaximalRectangle.maxArea(matrix));
  }
}