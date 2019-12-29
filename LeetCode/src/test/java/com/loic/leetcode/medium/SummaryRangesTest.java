package com.loic.leetcode.medium;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SummaryRangesTest {

  @Test
  void simple() {
    Assertions.assertEquals(Arrays.asList("0->2", "4->5", "7"), SummaryRanges.summaryRanges(0, 1, 2, 4, 5, 7));
    Assertions.assertEquals(Arrays.asList("0", "2->4", "6", "8->9"), SummaryRanges.summaryRanges(0, 2, 3, 4, 6, 8, 9));
    Assertions.assertEquals(Arrays.asList("0"), SummaryRanges.summaryRanges(0));
    Assertions.assertEquals(Arrays.asList("0->3"), SummaryRanges.summaryRanges(0, 1, 2, 3));
    Assertions.assertEquals(Arrays.asList("-2147483648", "2147483646->2147483647"), SummaryRanges.summaryRanges(Integer.MIN_VALUE, Integer.MAX_VALUE - 1, Integer.MAX_VALUE));
    Assertions.assertEquals(Arrays.asList(), SummaryRanges.summaryRanges());
  }
}