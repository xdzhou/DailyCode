package com.loic.exercise;

import com.loic.algo.common.Pair;
import com.loic.common.SolutionChecker;
import org.testng.annotations.Test;

public class CheckRotationStringTest {

  @Test
  public void test() {
    new SolutionChecker<>(new CheckRotationString())
        .check(Pair.of("sqf", "123"), false)
        .check(Pair.of("12345", "45123"), true)
        .check(Pair.of("12345", "12"), false)
        .check(Pair.of("azert", "ertaz"), true);
  }
}
