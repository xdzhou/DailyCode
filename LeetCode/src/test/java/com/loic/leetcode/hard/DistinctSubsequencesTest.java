package com.loic.leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DistinctSubsequencesTest {

  @Test
  void numDistinct() {
    Assertions.assertEquals(3, DistinctSubsequences.numDistinct("rabbbit", "rabbit"));
    Assertions.assertEquals(5, DistinctSubsequences.numDistinct("babgbag", "bag"));
  }

  @Test
  void dpSolution() {
    Assertions.assertEquals(3, DistinctSubsequences.dpSolution("rabbbit", "rabbit"));
    Assertions.assertEquals(5, DistinctSubsequences.dpSolution("babgbag", "bag"));
  }
}