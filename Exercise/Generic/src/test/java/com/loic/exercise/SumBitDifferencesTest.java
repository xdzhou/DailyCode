package com.loic.exercise;

import com.loic.solution.SolutionChecker;
import org.junit.jupiter.api.Test;

import static com.loic.solution.TestHelper.toArray;

public class SumBitDifferencesTest {

  @Test
  public void test() {
    SolutionChecker.create(new SumBitDifferences())
      .check(toArray(1, 2), 4)
      .check(toArray(1, 3, 5), 8);
  }
}
