package com.loic.leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UniqueBSTTest {

  @Test
  void numTrees() {
    Assertions.assertEquals(14, UniqueBST.numTrees(4));
    Assertions.assertEquals(1767263190, UniqueBST.numTrees(19));
  }
}