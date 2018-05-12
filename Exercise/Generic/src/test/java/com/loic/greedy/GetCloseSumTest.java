package com.loic.greedy;

import com.loic.algo.common.Pair;
import com.loic.solution.SolutionChecker;
import org.testng.annotations.Test;

import static com.loic.solution.TestHelper.toArray;

public class GetCloseSumTest {

  @Test
  public void test() {
    SolutionChecker.create(new GetCloseSum())
        .check(Pair.of(toArray(2, 2), toArray(1, 5)), 2)
        .check(Pair.of(toArray(100, 99, 98, 1, 2, 3), toArray(4, 5, 40, 1, 2, 3)), 48)
        .check(Pair.of(toArray(2, 2), toArray(1, 3)), 0)
        .check(Pair.of(toArray(1, 1, 2), toArray(8, 5, 3)), 0);
  }
}
