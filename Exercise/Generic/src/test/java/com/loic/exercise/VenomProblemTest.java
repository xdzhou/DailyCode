package com.loic.exercise;

import com.loic.common.SolutionChecker;
import org.testng.annotations.Test;

public class VenomProblemTest {

  @Test
  public void test() {
    new SolutionChecker<>(new VenomProblem())
        .check(1000, 10)
        .check(4, 2)
        .check(7, 3)
        .check(1024, 10)
        .check(16, 4);
  }
}
