package com.loic.leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CandyTest {

  @Test
  void test() {
    Assertions.assertEquals(8, Candy.candy(2, 1, 0, 1));
    Assertions.assertEquals(15, Candy.candy(0, 1, 2, 3, 1, 0, 1));
    Assertions.assertEquals(1, Candy.candy(1));
    Assertions.assertEquals(1, Candy.candy(10));
    Assertions.assertEquals(3, Candy.candy(0, 0, 0));
    Assertions.assertEquals(21, Candy.candy(1, 2, 3, 5, 4, 3, 2, 1));
  }
}