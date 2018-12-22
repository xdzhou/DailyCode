package com.loic.daily.exercise.dynamicProgramming;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LongestCommonSubsequenceTest {

  @Test
  void emptyString() {
    Assertions.assertEquals("", LongestCommonSubsequence.find("", ""));
    Assertions.assertEquals("", LongestCommonSubsequence.find("Az", ""));
    Assertions.assertEquals("", LongestCommonSubsequence.find("", "qsd"));
  }

  @Test
  void nomEmpty(){
    Assertions.assertEquals("ab", LongestCommonSubsequence.find("acb", "sab"));
    Assertions.assertEquals("aze", LongestCommonSubsequence.find("aze", "aze"));
    Assertions.assertEquals("abcd", LongestCommonSubsequence.find("abcd", "xaxbcdx"));
  }
}