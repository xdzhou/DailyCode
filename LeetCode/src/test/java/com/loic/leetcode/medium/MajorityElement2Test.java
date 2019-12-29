package com.loic.leetcode.medium;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MajorityElement2Test {

  @Test
  void simple() {
    Assertions.assertEquals(Arrays.asList(), MajorityElement2.majorityElement());
    Assertions.assertEquals(Arrays.asList(3), MajorityElement2.majorityElement(3));
    Assertions.assertEquals(Arrays.asList(3, 2), MajorityElement2.majorityElement(3, 2));
    Assertions.assertEquals(Arrays.asList(3), MajorityElement2.majorityElement(3, 2, 3));
    Assertions.assertEquals(Arrays.asList(), MajorityElement2.majorityElement(1, 5, 1, 3, 3, 2, 6, 2));
  }

  @Test
  void failed() {
    Assertions.assertEquals(Arrays.asList(1, 2), MajorityElement2.majorityElement(1, 1, 1, 3, 3, 2, 2, 2));
    Assertions.assertEquals(Arrays.asList(0), MajorityElement2.majorityElement(0, 0, 0));
  }
}