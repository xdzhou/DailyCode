package com.loic.leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ScrambleStringTest {

  @Test
  void isScramble() {
    Assertions.assertFalse(ScrambleString.isScramble("", "1"));
    Assertions.assertFalse(ScrambleString.isScramble("sf", "12"));
    Assertions.assertFalse(ScrambleString.isScramble("abcde", "caebd"));

    Assertions.assertTrue(ScrambleString.isScramble("great", "rgeat"));
    Assertions.assertTrue(ScrambleString.isScramble("great", "rgtae"));
  }
}