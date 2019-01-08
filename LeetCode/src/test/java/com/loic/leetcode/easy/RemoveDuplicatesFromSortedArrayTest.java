package com.loic.leetcode.easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RemoveDuplicatesFromSortedArrayTest {

  @Test
  void remove() {
    Assertions.assertEquals(2, RemoveDuplicatesFromSortedArray.remove(1, 1, 2));
  }
}