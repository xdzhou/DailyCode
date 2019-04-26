package com.loic.leetcode.medium;

import static com.loic.leetcode.TestHelper.toArray;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GasStationTest {

  @Test
  void test() {
    Assertions.assertEquals(3, GasStation.canCompleteCircuit(toArray(1, 2, 3, 4, 5), toArray(3, 4, 5, 1, 2)));
    Assertions.assertEquals(-1, GasStation.canCompleteCircuit(toArray(2, 3, 4), toArray(3, 4, 3)));
  }
}