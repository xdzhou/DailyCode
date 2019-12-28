package com.loic.leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BasicCalculatorTest {

  @Test
  void simple() {
    Assertions.assertEquals(12, BasicCalculator.calculate("12"));
    Assertions.assertEquals(12, BasicCalculator.calculate(" 12 "));
    Assertions.assertEquals(12, BasicCalculator.calculate("( 12) "));
    Assertions.assertEquals(1, BasicCalculator.calculate("1+2-2 "));
    Assertions.assertEquals(3, BasicCalculator.calculate(" 2-1 + 2 "));
    Assertions.assertEquals(23, BasicCalculator.calculate("(1+(4+5+2)-3)+(6+8)"));
  }

  @Test
  void failed() {
    Assertions.assertEquals(3, BasicCalculator.calculate("2-(5-6)"));
    Assertions.assertEquals(3, BasicCalculator.calculate("2-(5-(2-1)-6)-1"));
    Assertions.assertEquals(8, BasicCalculator.calculate("2-(0-(0-0)-6)-0"));
  }
}