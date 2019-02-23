package com.loic.leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MinimumWindowSubstringTest {

  @Test
  void window() {
    Assertions.assertEquals("BANC", MinimumWindowSubstring.window("ADOBECODEBANC", "ABC"));
    Assertions.assertEquals("", MinimumWindowSubstring.window("", "ABC"));
    Assertions.assertEquals("", MinimumWindowSubstring.window("az", "bc"));
    Assertions.assertEquals("xaaaax", MinimumWindowSubstring.window("aaxaaaaxa", "aaxx"));
    Assertions.assertEquals("cwae", MinimumWindowSubstring.window("cabwefgewcwaefgcf", "cae"));
  }
}