package com.loic.recursion;

import com.loic.common.SolutionChecker;
import org.testng.annotations.Test;

public class CombinationParenthesTest {

  @Test
  public void test() {
    new SolutionChecker<>(new CombinationParenthes())
        .check(1, 1)
        .check(2, 2)
        .check(3, 5);
  }
}
