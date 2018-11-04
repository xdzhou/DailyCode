package com.loic.recursion;

import com.loic.solution.SolutionChecker;
import org.junit.jupiter.api.Test;

class CombinationParenthesTest {

  @Test
  void test() {
    SolutionChecker.create(new CombinationParenthes())
      .check(1, 1)
      .check(2, 2)
      .check(3, 5);
  }
}
