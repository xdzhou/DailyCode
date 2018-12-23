package com.loic.daily.exercise.dynamicProgramming;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LongestCommonSubstringTest {

  @Test
  void enptyString() {
    Assertions.assertEquals("", LongestCommonSubstring.find("", ""));
    Assertions.assertEquals("", LongestCommonSubstring.find("", "az"));
    Assertions.assertEquals("", LongestCommonSubstring.find("qfd", ""));
  }

  @Test
  void nonString() {
    Assertions.assertEquals("aba", LongestCommonSubstring.find("abab", "baba"));
    Assertions.assertEquals("abc", LongestCommonSubstring.find("abc", "abcx"));
    Assertions.assertEquals("babc", LongestCommonSubstring.find("abababc", "abbabcd"));
  }
}