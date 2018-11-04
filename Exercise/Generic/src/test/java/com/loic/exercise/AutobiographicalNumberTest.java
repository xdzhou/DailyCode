package com.loic.exercise;

import com.loic.solution.SolutionChecker;
import org.junit.jupiter.api.Test;

class AutobiographicalNumberTest {

  @Test
  void test() {
    SolutionChecker.create(new AutobiographicalNumber())
      .checkInput(2020)
      .checkInput(1210)
      .checkInput(132545);
  }
}
