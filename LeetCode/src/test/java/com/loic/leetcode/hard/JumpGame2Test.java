package com.loic.leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JumpGame2Test {

  @Test
  void dpResolve() {
    Assertions.assertEquals(0, JumpGame2.dpResolve(1));
    Assertions.assertEquals(1, JumpGame2.dpResolve(2, 1));
    Assertions.assertEquals(2, JumpGame2.dpResolve(2, 3, 1, 4));
  }

  @Test
  void optimal() {
    Assertions.assertEquals(0, JumpGame2.optimal(1));
    Assertions.assertEquals(1, JumpGame2.optimal(2, 1));
    Assertions.assertEquals(2, JumpGame2.optimal(2, 3, 1, 4));
    Assertions.assertEquals(2, JumpGame2.optimal(2, 1, 1, 1));
  }
}