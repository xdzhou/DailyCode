package com.loic.divideConquer;

import com.loic.algo.common.Pair;
import com.loic.solution.SolutionChecker;
import org.junit.jupiter.api.Test;

public class DivideTwoIntegersTest {

  @Test
  public void test() {
    SolutionChecker.create(new DivideTwoIntegers())
      .check(Pair.of(1, 2), 1 / 2)
      .check(Pair.of(145, 12), 145 / 12)
      .check(Pair.of(1546, 0), Integer.MAX_VALUE)
      .check(Pair.of(111, 1), 111)
      .check(Pair.of(Integer.MAX_VALUE, Integer.MAX_VALUE), 1);
  }
}
