package com.loic.exercise;

import com.loic.algo.common.Pair;
import com.loic.solution.SolutionChecker;
import org.junit.jupiter.api.Test;

class CheckRotationStringTest {

  @Test
  void test() {
    SolutionChecker.create(new CheckRotationString())
      .check(Pair.of("sqf", "123"), false)
      .check(Pair.of("12345", "45123"), true)
      .check(Pair.of("12345", "12"), false)
      .check(Pair.of("azert", "ertaz"), true);
  }
}
