package com.loic.geeksforgeeks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LongestZigZagSubsequenceTest {

  @Test
  void test() {
    Assertions.assertEquals(3, LongestZigZagSubsequence.resolve(5, 5, 10, 100, 10, 5));
    Assertions.assertEquals(2, LongestZigZagSubsequence.resolve(1, 2, 3));
  }
}