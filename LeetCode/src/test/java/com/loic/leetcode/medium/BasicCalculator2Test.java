package com.loic.leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BasicCalculator2Test {

  @Test
  void simple() {
    Assertions.assertEquals(7, BasicCalculator2.calculate("3+5/2*2"));
    Assertions.assertEquals(7, BasicCalculator2.calculate("3+2*2"));
    Assertions.assertEquals(5, BasicCalculator2.calculate(" 3+5 / 2 "));
    Assertions.assertEquals(6, BasicCalculator2.calculate(" 3+5 - 2 "));
    Assertions.assertEquals(-2, BasicCalculator2.calculate(" 3/5 - 2 "));
    Assertions.assertEquals(2, BasicCalculator2.calculate(" 6/5 * 2 "));
    Assertions.assertEquals(0, BasicCalculator2.calculate("  "));
  }

  @Test
  void failed() {
    //Assertions.assertEquals(0, BasicCalculator2.calculate(" 0+0 "));
    //Assertions.assertEquals(0, BasicCalculator2.calculate(" 0 * 9 +0 "));
    Assertions.assertEquals(-24, BasicCalculator2.calculate("1*2-3/4+5*6-7*8+9/10"));
  }
}