package com.loic.exercise;

import com.loic.solution.SolutionChecker;
import org.junit.jupiter.api.Test;

public class UglyNumberTest {

  @Test
  public void test() {
    SolutionChecker.create(new UglyNumber())
      .checkInput(3)
      .checkInput(10)
      .checkInput(99)
      .checkInput(911);
  }
}
