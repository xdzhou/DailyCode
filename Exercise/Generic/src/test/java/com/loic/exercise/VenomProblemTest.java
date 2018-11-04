package com.loic.exercise;

import com.loic.solution.SolutionChecker;
import org.junit.jupiter.api.Test;

public class VenomProblemTest {

  @Test
  public void test() {
    SolutionChecker.create(new VenomProblem())
      .check(1000, 10)
      .check(4, 2)
      .check(7, 3)
      .check(1024, 10)
      .check(16, 4);
  }
}
