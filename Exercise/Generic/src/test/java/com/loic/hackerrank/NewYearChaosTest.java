package com.loic.hackerrank;

import com.loic.solution.SolutionChecker;
import org.testng.annotations.Test;

import static com.loic.solution.TestHelper.toArray;

public class NewYearChaosTest {

  @Test
  public void test() {
    new SolutionChecker<>(new NewYearChaos())
        .check(toArray(2, 1, 5, 3, 4), "3")
        .check(toArray(2, 5, 1, 3, 4), NewYearChaos.NO_ANSWER)
        .checkInput(toArray(2, 7, 3, 9, 8, 6, 5, 4, 1));
  }
}
