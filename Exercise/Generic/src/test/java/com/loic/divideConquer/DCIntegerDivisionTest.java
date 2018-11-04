package com.loic.divideConquer;

import com.loic.solution.SolutionChecker;
import org.junit.jupiter.api.Test;

class DCIntegerDivisionTest {

  @Test
  void testSimpleCase() {
    SolutionChecker.create(new DCIntegerDivision())
      .check(1, 1)
      .check(2, 2)
      .check(3, 3)
      .check(4, 5);
  }
}
