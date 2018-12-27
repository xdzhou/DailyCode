package com.loic.daily.exercise.dynamicProgramming;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LongestIncreasingSubsequenceTest {

  @Test
  void test() {
    algoTest(6, 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15);
    algoTest(4, 1, 5, 8, 3, 6, 7);
  }

  @Test
  void hasEqualNums() {
    //strictly increase
    algoTest(3, 1, 1, 1, 2, 3);
  }

  private void algoTest(int logest, int... nums) {
    Assertions.assertEquals(logest, LongestIncreasingSubsequence.dpResolve(nums));
    Assertions.assertEquals(logest, LongestIncreasingSubsequence.optimal(nums));
  }

}