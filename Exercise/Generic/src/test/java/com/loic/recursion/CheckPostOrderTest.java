package com.loic.recursion;

import com.loic.solution.SolutionChecker;
import org.junit.jupiter.api.Test;

import static com.loic.solution.TestHelper.toArray;

class CheckPostOrderTest {

  @Test
  void test() {
    SolutionChecker.create(new CheckPostOrder())
      .check(toArray(5, 7, 6, 9, 11, 10, 8), true)
      .check(toArray(1, 1, 1, 1, 1, 1, 1), true)
      .check(toArray(7, 4, 6, 5), false);
  }
}
