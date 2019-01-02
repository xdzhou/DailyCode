package com.loic.leetcode.medium;

import static com.loic.leetcode.TestHelper.toIntArray;

import java.util.Random;

import com.loic.leetcode.SolutionChecker;
import org.junit.jupiter.api.Test;


class ContainerMostWaterTest {
  private final SolutionChecker<int[], Integer> checker = SolutionChecker.create(ContainerMostWater::bruteForce, ContainerMostWater::optimal);

  @Test
  void test2lines() {
    checker.check(toIntArray(2, 3), 2)
      .check(toIntArray(30, 100), 30);
  }

  @Test
  void test3lines() {
    checker.check(toIntArray(2, 3, 8), 4)
      .check(toIntArray(30, 100, 10), 30);
  }

  @Test
  void testNlines() {
    Random random = new Random(0);
    int[] data = new int[1000];
    for (int i = 0; i < data.length; i++) {
      data[i] = random.nextInt() % 10 + 1;
    }
    checker.checkInput(data);
  }
}