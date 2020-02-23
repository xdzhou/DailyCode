package com.loic.leetcode.hard;

import static com.loic.leetcode.hard.ExpressionAddOperators.addOperators;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ExpressionAddOperatorsTest {
  @Test
  void simple() {
    checkResult("123", 6, "1+2+3", "1*2*3");
    checkResult("232", 8, "2*3+2", "2+3*2");
    checkResult("105", 5, "1*0+5", "10-5");
    checkResult("00", 0, "0+0", "0-0", "0*0");
    Assertions.assertTrue(addOperators("3456237490", 9191).isEmpty());
  }

  @Test
  void hasZero() {
    checkResult("012", 3, "0+1+2");
    checkResult("100", 0, "1*0+0", "1*0-0", "1*0*0", "10*0");
  }

  private void checkResult(String num, int target, String... expected) {
    List<String> result = addOperators(num, target);
    Assertions.assertEquals(expected.length, result.size());
    Set<String> set = new HashSet<>(result);
    for (String s : expected) {
      Assertions.assertTrue(set.contains(s));
    }
  }
}