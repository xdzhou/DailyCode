package com.loic.exercise;

import com.loic.solution.SolutionChecker;
import org.junit.jupiter.api.Test;

import static com.loic.solution.TestHelper.toArray;

public class FindMajorNumTest {

  @Test
  public void test() {
    SolutionChecker.create(new FindMajorNum())
      .check(toArray(1, 1, 2, 3, 5, 2, 3, 5, 1, 1, 1, 1, 1), 1)
      .check(toArray(2, 2, 2, 2), 2)
      .check(toArray(2, 1, 5, 2, 2, 8, 2), 2);
  }
}
