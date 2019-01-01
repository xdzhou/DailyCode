package com.loic.daily.exercise.dynamicProgramming;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MaxSubArrayTest {

  @Test
  void allNegative() {
    testCase(-1, -1, -2, -3, -4);
  }

  @Test
  void zeroWithNegative() {
    testCase(0, -1, -2, -3, -4, 0);
  }

  @Test
  void allPositive() {
    testCase(10, 1, 2, 3, 4);
  }

  @Test
  void positiveWithNegative() {
    testCase(6, -1, 2, -1, 4, -1, 2);
  }

  private void testCase(int maxSum, int... nums) {
    Assertions.assertEquals(maxSum, MaxSubArray.resolve(nums));
    Assertions.assertEquals(maxSum, MaxSubArray.dpResolveOptimal(nums));
    Assertions.assertEquals(maxSum, MaxSubArray.dpResolve(nums));
  }
}