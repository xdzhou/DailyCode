package com.loic.geeksforgeeks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StrictlyIncreaseArrayTest {

  @Test
  void test() {
    testCase(2, 1, 2, 3, 6, 5, 4);
    testCase(0, 1, 2, 3, 4, 5, 6, 7);
    testCase(5, 1, 2, 2, 2, 3, 4, 5);
  }

  private void testCase(int expected, int... nums) {
    Assertions.assertEquals(expected, StrictlyIncreaseArray.simpleResolve(nums));
    Assertions.assertEquals(expected, StrictlyIncreaseArray.resolve(nums));
  }

}