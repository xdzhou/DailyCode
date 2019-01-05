package com.loic.leetcode.easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ReverseIntegerTest {

  @Test
  void reverse() {
    Assertions.assertEquals(0, ReverseInteger.reverse(0));
    Assertions.assertEquals(1, ReverseInteger.reverse(1));
    Assertions.assertEquals(123, ReverseInteger.reverse(321));
    Assertions.assertEquals(-123, ReverseInteger.reverse(-321));
  }

  @Test
  void overflow() {
    Assertions.assertEquals(0, ReverseInteger.reverse(Integer.MIN_VALUE));
    Assertions.assertEquals(0, ReverseInteger.reverse(Integer.MAX_VALUE));
    Assertions.assertEquals(0, ReverseInteger.reverse(1999999999));
  }
}