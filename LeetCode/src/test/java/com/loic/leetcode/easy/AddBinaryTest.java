package com.loic.leetcode.easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AddBinaryTest {

  @Test
  void add() {
    Assertions.assertEquals("101", AddBinary.add("100", "1"));
    Assertions.assertEquals("100", AddBinary.add("11", "1"));
    Assertions.assertEquals("100001", AddBinary.add("100000", "1"));

    Assertions.assertEquals("10101", AddBinary.add("1010", "1011"));
  }
}