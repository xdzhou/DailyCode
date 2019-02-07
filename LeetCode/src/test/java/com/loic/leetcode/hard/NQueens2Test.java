package com.loic.leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NQueens2Test {

  @Test
  void count() {
    Assertions.assertEquals(1, NQueens2.count(1));
    Assertions.assertEquals(2, NQueens2.count(4));
  }
}