package com.loic.leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UniqueBST2Test {

  @Test
  void generateTrees() {
    Assertions.assertEquals(1, UniqueBST2.generateTrees(1).size());
    Assertions.assertEquals(2, UniqueBST2.generateTrees(2).size());
    Assertions.assertEquals(5, UniqueBST2.generateTrees(3).size());
  }
}