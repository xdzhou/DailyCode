package com.loic.leetcode.easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BestTimeBuySellStock2Test {

  @Test
  void maxProfit() {
    Assertions.assertEquals(0, BestTimeBuySellStock2.maxProfit());
    Assertions.assertEquals(0, BestTimeBuySellStock2.maxProfit(7));
    Assertions.assertEquals(0, BestTimeBuySellStock2.maxProfit(7, 1));
    Assertions.assertEquals(1, BestTimeBuySellStock2.maxProfit(7, 1, 2));

    Assertions.assertEquals(4, BestTimeBuySellStock2.maxProfit(1, 2, 3, 4, 5));
    Assertions.assertEquals(7, BestTimeBuySellStock2.maxProfit(7, 1, 5, 3, 6, 4));
  }
}