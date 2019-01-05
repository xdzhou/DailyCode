package com.loic.leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ZigZagConversionTest {

  @Test
  void convert() {
    Assertions.assertEquals("PAHNAPLSIIGYIR", ZigZagConversion.convert("PAYPALISHIRING", 3));
    Assertions.assertEquals("PINALSIGYAHRPI", ZigZagConversion.convert("PAYPALISHIRING", 4));
  }

  @Test
  void test() {
    Assertions.assertEquals("abcd", ZigZagConversion.convert("abcd", 1));
    Assertions.assertEquals("a", ZigZagConversion.convert("a", 3));
  }
}