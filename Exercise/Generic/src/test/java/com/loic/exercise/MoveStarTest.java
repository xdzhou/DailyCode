package com.loic.exercise;

import com.loic.solution.SolutionChecker;
import org.junit.jupiter.api.Test;

public class MoveStarTest {

  @Test
  public void test() {
    SolutionChecker.create(new MoveStar())
      .check("ab**cd**e*12", "*****abcde12")
      .check("*az*12*", "***az12")
      .check("*****", "*****")
      .check("12345", "12345");
  }
}
