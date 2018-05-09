package com.loic.greedy;

import com.loic.common.SolutionChecker;
import org.testng.annotations.Test;

import static com.loic.common.TestHelper.toArray;

public class MinComposeNumTest {

  @Test
  public void test() {
    new SolutionChecker<>(new MinComposeNum())
        .check(toArray(1, 2, 3, 4, 5, 6, 7), "1234567")
        .check(toArray(1, 11, 21, 12, 99), "111122199")
        .check(toArray(10, 15, 25, 85), "10152585")
        .check(toArray(1, 1, 1, 1, 1, 1, 1), "1111111");
  }
}
