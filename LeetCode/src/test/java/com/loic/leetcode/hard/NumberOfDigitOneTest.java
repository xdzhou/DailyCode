package com.loic.leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NumberOfDigitOneTest {

  @Test
  void simple() {
    Assertions.assertEquals(0, NumberOfDigitOne.countDigitOne(-1));
    Assertions.assertEquals(0, NumberOfDigitOne.countDigitOne(0));
    Assertions.assertEquals(6, NumberOfDigitOne.countDigitOne(13));
    Assertions.assertEquals(20, NumberOfDigitOne.countDigitOne(99));
    Assertions.assertEquals(301, NumberOfDigitOne.countDigitOne(1000));
  }
}