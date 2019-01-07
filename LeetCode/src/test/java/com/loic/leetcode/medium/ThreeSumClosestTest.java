package com.loic.leetcode.medium;

import static com.loic.leetcode.TestHelper.toIntArray;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ThreeSumClosestTest {

  @Test
  void test() {
    assertEquals(2, ThreeSumClosest.resolve(toIntArray(-1, 2, 1, -4), 1));
    assertEquals(2, ThreeSumClosest.resolve(toIntArray(1, 1, 1, 0), -100));
    assertEquals(13, ThreeSumClosest.resolve(toIntArray(1, 2, 5, 10, 11), 12));
  }

}