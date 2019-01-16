package com.loic.leetcode.easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CountAndSayTest {

  @Test
  void resolve() {
    Assertions.assertEquals("1", CountAndSay.resolve(1));
    Assertions.assertEquals("11", CountAndSay.resolve(2));
    Assertions.assertEquals("21", CountAndSay.resolve(3));
    Assertions.assertEquals("1211", CountAndSay.resolve(4));
    Assertions.assertEquals("111221", CountAndSay.resolve(5));
    Assertions.assertEquals("312211", CountAndSay.resolve(6));
  }
}