package com.loic.recursion;

import com.loic.solution.SolutionChecker;
import org.testng.annotations.Test;

public class NumberCountTest {

  @Test
  public void test() {
    SolutionChecker.create(new NumberCount())
        .checkInput(1235)
        .checkInput(1212);
  }

}
