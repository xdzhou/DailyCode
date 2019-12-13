package com.loic.leetcode.hard;

import static com.loic.leetcode.hard.BestTimeBuySellStock4.maxProfit;
import static com.loic.leetcode.hard.BestTimeBuySellStock4.maxProfit2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BestTimeBuySellStock4Test {

  @Test
  void simple() {
    Assertions.assertEquals(0, maxProfit(12));
    Assertions.assertEquals(0, maxProfit(0, 4));
    Assertions.assertEquals(0, maxProfit(0, 4, 2));
    Assertions.assertEquals(0, maxProfit(1, 4, 2));
    Assertions.assertEquals(1, maxProfit(1, 1, 2));
    Assertions.assertEquals(0, maxProfit(1, 1, 1));

    Assertions.assertEquals(2, maxProfit(2, 2, 4, 1));
    Assertions.assertEquals(2, maxProfit(2000, 2, 4, 1));
    Assertions.assertEquals(7, maxProfit(2, 3, 2, 6, 5, 0, 3));
    Assertions.assertEquals(6, maxProfit(2, 3, 3, 5, 0, 0, 3, 1, 4));
    Assertions.assertEquals(13, maxProfit(2, 1, 2, 4, 2, 5, 7, 2, 4, 9, 0));
    Assertions.assertEquals(17, maxProfit(2, 1, 2, 4, 2, 5, 7, 2, 4, 9, 0, 9));
  }

  @Test
  void simple2() {
    Assertions.assertEquals(0, maxProfit2(12));
    Assertions.assertEquals(0, maxProfit2(0, 4));
    Assertions.assertEquals(0, maxProfit2(0, 4, 2));
    Assertions.assertEquals(0, maxProfit2(1, 4, 2));
    Assertions.assertEquals(1, maxProfit2(1, 1, 2));
    Assertions.assertEquals(0, maxProfit2(1, 1, 1));

    Assertions.assertEquals(2, maxProfit2(2, 2, 4, 1));
    Assertions.assertEquals(2, maxProfit2(2000, 2, 4, 1));
    Assertions.assertEquals(7, maxProfit2(2, 3, 2, 6, 5, 0, 3));
    Assertions.assertEquals(6, maxProfit2(2, 3, 3, 5, 0, 0, 3, 1, 4));
    Assertions.assertEquals(13, maxProfit2(2, 1, 2, 4, 2, 5, 7, 2, 4, 9, 0));
    Assertions.assertEquals(17, maxProfit2(2, 1, 2, 4, 2, 5, 7, 2, 4, 9, 0, 9));
  }
}