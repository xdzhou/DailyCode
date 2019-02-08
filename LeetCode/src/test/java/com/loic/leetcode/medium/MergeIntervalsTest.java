package com.loic.leetcode.medium;

import static com.loic.leetcode.medium.MergeIntervals.merge;

import com.loic.leetcode.TestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MergeIntervalsTest {

  @Test
  void test() {
    Assertions.assertEquals(TestHelper.intervals(1, 6, 8, 10, 15, 18), merge(TestHelper.intervals(1, 3, 2, 6, 8, 10, 15, 18)));
    Assertions.assertEquals(TestHelper.intervals(1, 5), merge(TestHelper.intervals(1, 4, 4, 5)));

    Assertions.assertEquals(TestHelper.intervals(0, 4), merge(TestHelper.intervals(1, 4, 0, 4)));
    Assertions.assertEquals(TestHelper.intervals(), merge(TestHelper.intervals()));
  }
}