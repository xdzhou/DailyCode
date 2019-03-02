package com.loic.leetcode.easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ClimbingStairsTest {

  @Test
  void steps() {
    Assertions.assertEquals(1, ClimbingStairs.steps(1));
    Assertions.assertEquals(2, ClimbingStairs.steps(2));
    Assertions.assertEquals(3, ClimbingStairs.steps(3));
  }
}