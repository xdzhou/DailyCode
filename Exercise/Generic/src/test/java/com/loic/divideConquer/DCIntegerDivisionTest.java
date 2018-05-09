package com.loic.divideConquer;

import com.loic.solution.SolutionChecker;
import org.testng.annotations.Test;

public class DCIntegerDivisionTest {

  @Test
  public void testSimpleCase() {
    new SolutionChecker<>(new DCIntegerDivision())
        .check(1, 1)
        .check(2, 2)
        .check(3, 3)
        .check(4, 5);
  }
}
