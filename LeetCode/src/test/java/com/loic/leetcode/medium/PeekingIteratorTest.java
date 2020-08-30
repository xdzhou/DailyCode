package com.loic.leetcode.medium;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PeekingIteratorTest {

  @Test
  void peek() {
    PeekingIterator peekingIterator = new PeekingIterator(Arrays.asList(1, 2, 3).iterator());
    Assertions.assertEquals(1, peekingIterator.next().intValue());
    Assertions.assertEquals(2, peekingIterator.peek().intValue());
    Assertions.assertEquals(2, peekingIterator.peek().intValue());
    Assertions.assertEquals(2, peekingIterator.next().intValue());
    Assertions.assertEquals(3, peekingIterator.peek().intValue());
    Assertions.assertTrue(peekingIterator.hasNext());
  }
}