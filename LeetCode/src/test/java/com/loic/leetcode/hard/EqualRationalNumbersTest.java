package com.loic.leetcode.hard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EqualRationalNumbersTest {

  @Test
  void test() {
    assertTrue(EqualRationalNumbers.isRationalEqual("0.(52)", "0.5(25)"));
    assertTrue(EqualRationalNumbers.isRationalEqual("0.1666(6)", "0.166(66)"));
    assertTrue(EqualRationalNumbers.isRationalEqual("0.9(9)", "1."));
    assertTrue(EqualRationalNumbers.isRationalEqual("1.0", "1"));
    assertTrue(EqualRationalNumbers.isRationalEqual("0.(0)", "0."));
  }

  @Test
  void simplify() {
    assertEquals("1", EqualRationalNumbers.simplify("0.9999(9)"));
    assertEquals("0.9(123)", EqualRationalNumbers.simplify("0.912(312)"));
  }

  @Test
  void simplifyRepeatPart() {
    assertEquals("123", EqualRationalNumbers.simplifyRepeatPart("123"));
    assertEquals("12", EqualRationalNumbers.simplifyRepeatPart("1212"));
    assertEquals("2", EqualRationalNumbers.simplifyRepeatPart("2222"));
  }
}