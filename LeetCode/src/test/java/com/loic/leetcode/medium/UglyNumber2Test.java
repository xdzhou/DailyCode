package com.loic.leetcode.medium;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UglyNumber2Test {

  @Test
  void simple() {
    List<Integer> uglyNums = Arrays.asList(1, 2, 3, 4, 5, 6, 8, 9, 10, 12);
    for (int i = 0; i < uglyNums.size(); i++) {
      Assertions.assertEquals(uglyNums.get(i).intValue(), UglyNumber2.nthUglyNumber(i + 1));
    }
  }

  @Test
  void failed() {
    Assertions.assertEquals(536870912, UglyNumber2.nthUglyNumber(1407));
  }
}