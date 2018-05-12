package com.loic.dynamicProgramming;

import com.loic.solution.SolutionChecker;
import org.testng.annotations.Test;

import static com.loic.solution.TestHelper.toArray;

public class FindMidNumTest {

  @Test
  public void test() {
    SolutionChecker.create(new FindMidNum())
        .check(toArray(3, 2, 1, 4, 6, 5, 7), 4)
        .check(toArray(2, 2, 2, 2, 2), 2)
        .check(toArray(1, 2, 3), 2);
  }
}
