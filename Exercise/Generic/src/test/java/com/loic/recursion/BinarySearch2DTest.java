package com.loic.recursion;

import com.loic.algo.common.Pair;
import com.loic.solution.SolutionChecker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BinarySearch2DTest {

  @Test
  void test() {
    Integer[][] table = createTable(100);
    SolutionChecker.create(new BinarySearch2D<Integer>())
      .check(Pair.of(table, 2), this::onOutputReady)
      .check(Pair.of(table, 63), this::onOutputReady);
  }

  private void onOutputReady(Pair<Integer[][], Integer> input, Pair<Integer, Integer> output) {
    Assertions.assertEquals(input.first()[output.first()][output.second()], input.second());
  }

  private Integer[][] createTable(int n) {
    Integer[][] table = new Integer[n][n];
    int val = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        table[i][j] = val++;
      }
    }
    return table;
  }
}
