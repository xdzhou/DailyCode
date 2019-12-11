package com.loic.leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CourseScheduleTest {

  @Test
  void simple() {
    testCanFinish(true, 1);
    testCanFinish(true, 2, 0, 1);
    testCanFinish(false, 1, 0, 0);
    testCanFinish(false, 2, 0, 1, 1, 0);
    testCanFinish(true, Integer.MAX_VALUE, 0, 1, 1, 100);
  }

  private void testCanFinish(boolean expected, int num, int... dependency) {
    int pairCount = dependency.length / 2;
    int[][] prerequisites = new int[pairCount][];
    for (int i = 0; i < pairCount; i++) {
      int[] pair = new int[2];
      pair[0] = dependency[i * 2];
      pair[1] = dependency[i * 2 + 1];
      prerequisites[i] = pair;
    }
    Assertions.assertEquals(expected, CourseSchedule.canFinish(num, prerequisites));
  }
}