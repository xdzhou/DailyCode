package com.loic.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Integer2RomanTest {

  @Test
  void convert() {
    assertEquals("III", Integer2Roman.convert(3));
    assertEquals("IV", Integer2Roman.convert(4));
    assertEquals("IX", Integer2Roman.convert(9));
    assertEquals("LVIII", Integer2Roman.convert(58));
    assertEquals("MCMXCIV", Integer2Roman.convert(1994));
  }
}