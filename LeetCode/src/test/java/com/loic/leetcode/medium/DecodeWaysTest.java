package com.loic.leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DecodeWaysTest {

  @Test
  void num() {
    Assertions.assertEquals(0, DecodeWays.num(""));
    Assertions.assertEquals(0, DecodeWays.num("301"));
    Assertions.assertEquals(1, DecodeWays.num("120"));
    Assertions.assertEquals(2, DecodeWays.num("12"));
    Assertions.assertEquals(3, DecodeWays.num("226"));
  }
}