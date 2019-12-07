package com.loic.leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Fraction2RecurringDecimalTest {

  @Test
  void simple() {
    Assertions.assertEquals("0", Fraction2RecurringDecimal.fraction2Decimal(0, 11));
    Assertions.assertEquals("0.2", Fraction2RecurringDecimal.fraction2Decimal(1, 5));
    Assertions.assertEquals("0.25", Fraction2RecurringDecimal.fraction2Decimal(1, 4));
    Assertions.assertEquals("0.(3)", Fraction2RecurringDecimal.fraction2Decimal(1, 3));

    Assertions.assertEquals("-0.(3)", Fraction2RecurringDecimal.fraction2Decimal(-1, 3));
    Assertions.assertEquals("1", Fraction2RecurringDecimal.fraction2Decimal(Integer.MIN_VALUE, Integer.MIN_VALUE));
  }

  @Test
  void failed() {
    Assertions.assertEquals("-6.25", Fraction2RecurringDecimal.fraction2Decimal(-50, 8));
    Assertions.assertEquals(
      "0.0000000004656612873077392578125", Fraction2RecurringDecimal.fraction2Decimal(-1, -2147483648));
  }
}