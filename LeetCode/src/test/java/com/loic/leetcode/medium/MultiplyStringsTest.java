package com.loic.leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MultiplyStringsTest {

  @Test
  void multiply() {
    Assertions.assertEquals("6", MultiplyStrings.multiply("2", "3"));
    Assertions.assertEquals("0", MultiplyStrings.multiply("0", "465"));
    Assertions.assertEquals("46500", MultiplyStrings.multiply("100", "465"));
    Assertions.assertEquals("57195", MultiplyStrings.multiply("123", "465"));
  }
}