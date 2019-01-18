package com.loic.leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TrappingRainWaterTest {

  @Test
  void trap() {
    Assertions.assertEquals(0, TrappingRainWater.trap());
    Assertions.assertEquals(6, TrappingRainWater.trap(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1));

  }
}