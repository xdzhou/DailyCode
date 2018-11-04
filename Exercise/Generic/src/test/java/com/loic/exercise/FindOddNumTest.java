package com.loic.exercise;

import com.loic.solution.SolutionChecker;
import org.junit.jupiter.api.Test;

import static com.loic.solution.TestHelper.toArray;

public class FindOddNumTest {

  @Test
  public void test() {
    SolutionChecker.create(new FindOddNum())
      .check(toArray(1, 2, 2), 1)
      .check(toArray(1, 1, 2, 2, 2, 3, 4, 4, 3), 2);
  }
}
