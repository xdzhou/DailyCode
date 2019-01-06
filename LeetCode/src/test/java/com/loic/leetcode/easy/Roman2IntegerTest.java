package com.loic.leetcode.easy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Roman2IntegerTest {

  @Test
  void convert() {
    assertEquals(3, Roman2Integer.convert("III"));
    assertEquals(4, Roman2Integer.convert("IV"));
    assertEquals(9, Roman2Integer.convert("IX"));
    assertEquals(58, Roman2Integer.convert("LVIII"));
    assertEquals(1994, Roman2Integer.convert("MCMXCIV"));
  }
}