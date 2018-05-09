package com.loic.hackerrank;

import com.loic.common.SolutionChecker;
import org.testng.annotations.Test;

public class BiggerIsGreaterTest {

  @Test
  public void test() {
    new SolutionChecker<>(new BiggerIsGreater())
        .check("ab", "ba")
        .check("bb", BiggerIsGreater.NO_ANSWER)
        .check("hefg", "hegf")
        .check("dhck", "dhkc")
        .check("87654", BiggerIsGreater.NO_ANSWER)
        .check("01265330", "01302356")
        .check("123", "132");
  }
}
