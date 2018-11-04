package com.loic.exercise;

import com.loic.solution.SolutionChecker;
import org.junit.jupiter.api.Test;

public class SumIsContinuousNumSequenceTest {

  @Test
  public void test() {
    SolutionChecker.create(new SumIsContinuousNumSequence())
      .check(15, 3)
      .check(3, 1);
  }
}
