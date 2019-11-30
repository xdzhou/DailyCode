package com.loic.leetcode.easy;

import static java.util.Collections.singletonList;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PascalTriangleTest {

  @Test
  void generate() {
    Assertions.assertEquals(singletonList(singletonList(1)), PascalTriangle.generate(1));
    Assertions.assertEquals(
      Arrays.asList(singletonList(1), Arrays.asList(1, 1), Arrays.asList(1, 2, 2)),
      PascalTriangle.generate(3));
  }
}