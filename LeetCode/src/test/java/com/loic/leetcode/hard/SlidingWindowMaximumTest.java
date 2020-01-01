package com.loic.leetcode.hard;

import com.loic.leetcode.TestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SlidingWindowMaximumTest {

  @Test
  void simple() {
    Assertions.assertArrayEquals(TestHelper.toArray(1, 2, 3), SlidingWindowMaximum.maxSlidingWindow(1, 1, 2, 3));
    Assertions.assertArrayEquals(TestHelper.toArray(2, 3), SlidingWindowMaximum.maxSlidingWindow(2, 1, 2, 3));
    Assertions.assertArrayEquals(TestHelper.toArray(3), SlidingWindowMaximum.maxSlidingWindow(3, 1, 2, 3));
    Assertions.assertArrayEquals(TestHelper.toArray(3, 3, 5, 5, 6, 7), SlidingWindowMaximum.maxSlidingWindow(3, 1, 3, -1, -3, 5, 3, 6, 7));
  }
}