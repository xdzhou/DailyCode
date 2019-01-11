package com.loic.leetcode.easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RemoveElementTest {

  @Test
  void resolve() {
    Assertions.assertEquals(2, RemoveElement.resolve(3, 3, 2, 2, 3));
    Assertions.assertEquals(5, RemoveElement.resolve(2, 0, 1, 2, 2, 3, 0, 4, 2));
  }
}