package com.loic.leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BurstBalloonsTest {

  @Test
  void maxCoins() {
    Assertions.assertEquals(167, BurstBalloons.maxCoins(3, 1, 5, 0, 8));
    Assertions.assertEquals(167, BurstBalloons.maxCoins(3, 1, 5, 8));
    Assertions.assertEquals(0, BurstBalloons.maxCoins());
    Assertions.assertEquals(8, BurstBalloons.maxCoins(8));
    Assertions.assertEquals(90, BurstBalloons.maxCoins(10, 8));
  }

  @Test
  void failed() {
    Assertions.assertEquals(1086136, BurstBalloons.maxCoins(9, 76, 64, 21, 97, 60));
  }
}