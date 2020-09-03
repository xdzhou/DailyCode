package com.loic.leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedianFinderTest {

  @Test
  void addNum() {
    MedianFinder finder = new MedianFinder();
    Assertions.assertEquals(0d, finder.findMedian());
    finder.addNum(1);
    Assertions.assertEquals(1d, finder.findMedian());
    finder.addNum(2);
    Assertions.assertEquals(1.5d, finder.findMedian());
    finder.addNum(3);
    Assertions.assertEquals(2, finder.findMedian());
  }
}