package com.loic.leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HouseRobber2Test {

  @Test
  void simple() {
    Assertions.assertEquals(0, HouseRobber2.rob());
    Assertions.assertEquals(3, HouseRobber2.rob(3));
    Assertions.assertEquals(3, HouseRobber2.rob(2, 3));
    Assertions.assertEquals(3, HouseRobber2.rob(2, 3, 2));
    Assertions.assertEquals(4, HouseRobber2.rob(1, 2, 3, 1));
    Assertions.assertEquals(12, HouseRobber2.rob(6, 7, 6, 3, 1));
  }
}