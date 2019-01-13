package com.loic.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DivideTwoIntegersTest {

  @Test
  void test() {
    assertEquals(3, DivideTwoIntegers.resolve(10, 3));
    assertEquals(3, DivideTwoIntegers.resolve(9, 3));
    assertEquals(9, DivideTwoIntegers.resolve(9, 1));
    assertEquals(-9, DivideTwoIntegers.resolve(9, -1));
    assertEquals(-3, DivideTwoIntegers.resolve(-9, 3));
    assertEquals(0, DivideTwoIntegers.resolve(-1010369383, -2147483648));
    assertEquals(Integer.MAX_VALUE, DivideTwoIntegers.resolve(Integer.MIN_VALUE, -1));
    assertEquals(Integer.MIN_VALUE, DivideTwoIntegers.resolve(Integer.MIN_VALUE, 1));
  }
}