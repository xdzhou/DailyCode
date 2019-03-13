package com.loic.leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class InterleavingStringTest {

  @Test
  void isInterleave() {
    Assertions.assertTrue(InterleavingString.isInterleave("", "", ""));
    Assertions.assertTrue(InterleavingString.isInterleave("a", "", "a"));
    Assertions.assertTrue(InterleavingString.isInterleave("a", "b", "ab"));
    Assertions.assertTrue(InterleavingString.isInterleave("aabcc", "dbbca", "aadbbcbcac"));
    Assertions.assertTrue(InterleavingString.isInterleave("a", "ac", "aca"));
  }
}