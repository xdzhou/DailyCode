package com.loic.exercise;

import com.loic.common.SolutionChecker;
import org.testng.annotations.Test;

public class TrailingZerosTest {

  @Test
  public void test() {
    SolutionChecker<Integer, Integer> checker = new SolutionChecker<>(new TrailingZeros());
    for (int i = 0; i < 100; i++) {
      checker.checkInput(i * 6);
    }
  }
}
