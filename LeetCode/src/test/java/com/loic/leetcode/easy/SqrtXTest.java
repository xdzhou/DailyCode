package com.loic.leetcode.easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SqrtXTest {

  @Test
  void sqrt() {
    Assertions.assertEquals(0, SqrtX.sqrt(0));
    Assertions.assertEquals(1, SqrtX.sqrt(1));
    Assertions.assertEquals(1, SqrtX.sqrt(2));
    Assertions.assertEquals(2, SqrtX.sqrt(4));
    Assertions.assertEquals(2, SqrtX.sqrt(8));

    Assertions.assertEquals(100, SqrtX.sqrt(10001));
  }

  @Test
  void leetcode() {
    Assertions.assertEquals(46339, SqrtX.sqrt(2147395599));
  }
}