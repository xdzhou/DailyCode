package com.loic.leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EditDistanceTest {

  @Test
  void minDistance() {
    Assertions.assertEquals(3, EditDistance.minDistance("horse", "ros"));
    Assertions.assertEquals(5, EditDistance.minDistance("intention", "execution"));
  }
}