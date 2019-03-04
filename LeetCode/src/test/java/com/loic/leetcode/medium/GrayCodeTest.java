package com.loic.leetcode.medium;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GrayCodeTest {

  @Test
  void grayCode() {
    List<Integer> expected = Arrays.asList(0, 1);
    Assertions.assertEquals(expected, GrayCode.grayCode(1));
    Assertions.assertEquals(expected, GrayCode.grayCodeOptimal(1));
  }
}