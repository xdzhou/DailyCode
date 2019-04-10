package com.loic.leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LongestConsecutiveSequenceTest {

  @Test
  void test() {
    Assertions.assertEquals(4, LongestConsecutiveSequence.maxLen(12, 1, 99, 2, 8, 3, 77, 4, 56));
    Assertions.assertEquals(1, LongestConsecutiveSequence.maxLen(1));
    Assertions.assertEquals(1, LongestConsecutiveSequence.maxLen(1, 3));
    Assertions.assertEquals(2, LongestConsecutiveSequence.maxLen(1, 2));
    Assertions.assertEquals(0, LongestConsecutiveSequence.maxLen());
  }
}