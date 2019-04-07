package com.loic.leetcode.medium;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TriangleTest {

  @Test
  void empty() {
    Assertions.assertEquals(0, Triangle.minimumTotal(Collections.emptyList()));
  }

  @Test
  void test() {
    Assertions.assertEquals(5, Triangle.minimumTotal(Collections.singletonList(Collections.singletonList(5))));
    Assertions.assertEquals(11, Triangle.minimumTotal(Arrays.asList(Arrays.asList(2),
      Arrays.asList(3, 4),
      Arrays.asList(6, 5, 7),
      Arrays.asList(4, 1, 8, 3))));
  }
}