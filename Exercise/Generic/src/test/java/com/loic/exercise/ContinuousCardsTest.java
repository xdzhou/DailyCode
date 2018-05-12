package com.loic.exercise;

import com.loic.solution.SolutionChecker;
import org.testng.annotations.Test;

import static com.loic.solution.TestHelper.toArray;

public class ContinuousCardsTest {

  @Test
  public void test() {
    SolutionChecker.create(new ContinuousCards())
        .check(toArray(1), true)
        .check(toArray(1, 2, 3), true)
        .check(toArray(1, 2, 5), false)
        .check(toArray(0, 2, 4, 5), true)
        .check(toArray(0, 3, 4, 7), false)
        .check(toArray(0, 0), true)
        .check(toArray(0, 0, 2), true)
        .check(toArray(0, 0, 2, 4), true)
        .check(toArray(0, 0, 2, 5, 6, 7), true);
  }
}
