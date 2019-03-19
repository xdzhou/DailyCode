package com.loic.leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BestTimeBuySellStock3Test {

  @Test
  void maxProfit() {
    Assertions.assertEquals(6, BestTimeBuySellStock3.maxProfit(3, 3, 5, 0, 0, 3, 1, 4));
    Assertions.assertEquals(0, BestTimeBuySellStock3.maxProfit(3, 2, 1));
    Assertions.assertEquals(1, BestTimeBuySellStock3.maxProfit(1, 2));
    Assertions.assertEquals(4, BestTimeBuySellStock3.maxProfit(1, 2, 3, 4, 5));
    Assertions.assertEquals(0, BestTimeBuySellStock3.maxProfit(1));
    Assertions.assertEquals(0, BestTimeBuySellStock3.maxProfit());
  }
}