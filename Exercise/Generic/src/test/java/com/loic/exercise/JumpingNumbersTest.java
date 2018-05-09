package com.loic.exercise;

import com.loic.solution.SolutionChecker;
import org.testng.annotations.Test;

public class JumpingNumbersTest {

  @Test
  public void test() {
    new SolutionChecker<>(new JumpingNumbers())
        .check(9, 9)
        .check(20, 12)
        .check(105, 28);
  }
}
