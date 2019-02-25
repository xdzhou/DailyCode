package com.loic.leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RemoveDuplicatesSortedArray2Test {

  @Test
  void remove() {
    Assertions.assertEquals(5, RemoveDuplicatesSortedArray2.remove(1, 1, 1, 1, 2, 2, 3));
  }
}