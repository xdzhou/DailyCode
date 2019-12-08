package com.loic.leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DungeonGameTest {

  @Test
  void simple() {
    Assertions.assertEquals(1, DungeonGame.minimumHP(oneOneArray(0)));
    Assertions.assertEquals(1, DungeonGame.minimumHP(oneOneArray(1)));
    Assertions.assertEquals(4, DungeonGame.minimumHP(oneOneArray(-3)));

    int[][] nums = new int[3][];
    nums[0] = new int[]{-2, -3, 3};
    nums[1] = new int[]{-5, -10, 1};
    nums[2] = new int[]{10, 30, -5};
    Assertions.assertEquals(7, DungeonGame.minimumHP(nums));
  }

  private int[][] oneOneArray(int num) {
    int[][] nums = new int[1][1];
    nums[0][0] = num;
    return nums;
  }
}