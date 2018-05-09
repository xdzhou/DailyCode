package com.loic.exercise;

import com.loic.common.SolutionChecker;
import org.testng.annotations.Test;

import static com.loic.common.TestHelper.toArray;

public class FindOddNumTest {

  @Test
  public void test() {
    new SolutionChecker<>(new FindOddNum())
        .check(toArray(1, 2, 2), 1)
        .check(toArray(1, 1, 2, 2, 2, 3, 4, 4, 3), 2);
  }
}
