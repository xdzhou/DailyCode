package com.loic.dynamicProgramming;

import com.loic.solution.SolutionChecker;
import org.testng.annotations.Test;

import static com.loic.solution.TestHelper.toArray;

public class DPLCSTest {

  @Test
  public void test() {
    new SolutionChecker<>(new DPLCS())
        .check(toArray("abcdefg", "apcmzf"), "acf")
        .check(toArray("123654987", "zfddsf"), (String) null)
        .check(toArray("1234", "34"), "34");
  }
}
