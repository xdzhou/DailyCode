package com.loic.exercise;

import com.loic.solution.SolutionChecker;
import org.testng.annotations.Test;

public class AdditiveNumberTest {

  @Test
  public void test() {
    SolutionChecker.create(new AdditiveNumber())
        .check("112358", true)
        .check("199100199", true)
        .check("123", true)
        .check("000000", true)
        .check("00654160000", false);
  }
}
