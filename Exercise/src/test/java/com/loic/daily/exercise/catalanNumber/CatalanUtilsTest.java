package com.loic.daily.exercise.catalanNumber;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CatalanUtilsTest {

  @Test
  void catalan() {
    for (int i = 0; i < 10; i++) {
      Assertions.assertEquals(parentheseCount(i), CatalanUtils.catalan(i));
    }
  }

  @Test
  void binomialCoefficient(){
    Assertions.assertEquals(CatalanUtils.binomialCoefficient(10, 7), CatalanUtils.binomialCoefficient(10, 3));
  }

  private long parentheseCount(int n) {
    return parentheseCount(n, n);
  }

  private long parentheseCount(int open, int close) {
    if (open == close && open == 0) {
      return 1;
    }
    if (open >= close) {
      return parentheseCount(open - 1, close);
    } else {
      long firstPart = open > 0 ? parentheseCount(open - 1, close) : 0;
      long secondPart = close > 0 ? parentheseCount(open, close - 1) : 0;
      return firstPart + secondPart;
    }

  }
}