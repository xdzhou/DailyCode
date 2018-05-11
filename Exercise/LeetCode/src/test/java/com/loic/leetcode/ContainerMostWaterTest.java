package com.loic.leetcode;

import com.loic.solution.SolutionChecker;
import com.loic.solution.TestHelper;
import org.junit.Test;

import java.util.Random;


public class ContainerMostWaterTest {
  private final SolutionChecker<int[], Integer> checker = SolutionChecker.checker(new ContainerMostWater());

  @Test
  public void test2lines() {
    checker.check(TestHelper.toIntArray(2, 3), 2)
      .check(TestHelper.toIntArray(30, 100), 30);
  }

  @Test
  public void test3lines() {
    checker.check(TestHelper.toIntArray(2, 3, 8), 4)
      .check(TestHelper.toIntArray(30, 100, 10), 30);
  }

  @Test
  public void testNlines() {
    Random random = new Random(0);
    int[] data = new int[1000];
    for(int i = 0; i < data.length; i++) {
      data[i] = random.nextInt() % 10 + 1;
    }
    checker.checkInput(data);
  }
}