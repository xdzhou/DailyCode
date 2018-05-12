package com.loic.exercise;

import com.loic.solution.SolutionChecker;
import org.testng.annotations.Test;

public class TrailingZerosTest {

  @Test
  public void test() {
    SolutionChecker<Integer, Integer> checker = SolutionChecker.create(new TrailingZeros());
    for (int i = 0; i < 100; i++) {
      checker.checkInput(i * 6);
    }
  }
}
