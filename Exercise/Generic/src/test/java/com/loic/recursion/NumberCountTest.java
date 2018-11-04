package com.loic.recursion;

import com.loic.solution.SolutionChecker;
import org.junit.jupiter.api.Test;

class NumberCountTest {

  @Test
  void test() {
    SolutionChecker.create(new NumberCount())
      .checkInput(1235)
      .checkInput(1212);
  }

}
