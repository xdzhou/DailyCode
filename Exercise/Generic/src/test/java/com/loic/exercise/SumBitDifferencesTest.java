package com.loic.exercise;

import com.loic.solution.SolutionChecker;
import org.testng.annotations.Test;

import static com.loic.solution.TestHelper.toArray;

public class SumBitDifferencesTest {

  @Test
  public void test() {
    new SolutionChecker<>(new SumBitDifferences())
        .check(toArray(1, 2), 4)
        .check(toArray(1, 3, 5), 8);
  }
}
