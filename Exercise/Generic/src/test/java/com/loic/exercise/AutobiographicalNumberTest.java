package com.loic.exercise;

import com.loic.solution.SolutionChecker;
import org.testng.annotations.Test;

public class AutobiographicalNumberTest {

  @Test
  public void test() {
    SolutionChecker.create(new AutobiographicalNumber())
        .checkInput(2020)
        .checkInput(1210)
        .checkInput(132545);
  }
}
