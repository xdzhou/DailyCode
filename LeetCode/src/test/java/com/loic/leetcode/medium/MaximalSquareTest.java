package com.loic.leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MaximalSquareTest {

  @Test
  void simple() {
    Assertions.assertEquals(4, MaximalSquare.maximalSquare(generateInputs("10100", "10111", "11111", "10010")));
  }

  private char[][] generateInputs(String... lines) {
    char[][] data = new char[lines.length][];
    for (int i = 0; i < lines.length; i++) {
      data[i] = lines[i].toCharArray();
    }
    return data;
  }
}