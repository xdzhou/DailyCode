package com.loic.leetcode.hard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UniquePathsIIITest {

  @Test
  void resolve() {
    assertEquals(2, UniquePathsIII.resolve(new int[][]{{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 2, -1}}));
    assertEquals(4, UniquePathsIII.resolve(new int[][]{{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 2}}));
    assertEquals(0, UniquePathsIII.resolve(new int[][]{{0, 1}, {2, 0}}));
  }
}