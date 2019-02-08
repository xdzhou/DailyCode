package com.loic.leetcode.hard;

import static com.loic.leetcode.hard.InsertInterval.insert;

import com.loic.leetcode.TestHelper;
import com.loic.leetcode.helper.Interval;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class InsertIntervalTest {

  @Test
  void test() {
    Assertions.assertEquals(TestHelper.intervals(1, 5), insert(TestHelper.intervals(), new Interval(1, 5)));
    Assertions.assertEquals(TestHelper.intervals(1, 5, 6, 9), insert(TestHelper.intervals(1, 3, 6, 9), new Interval(2, 5)));
    Assertions.assertEquals(TestHelper.intervals(1, 2, 3, 10, 12, 16),
      insert(TestHelper.intervals(1, 2, 3, 5, 6, 7, 8, 10, 12, 16), new Interval(4, 8)));

    Assertions.assertEquals(TestHelper.intervals(0, 1, 2, 5), insert(TestHelper.intervals(0, 1), new Interval(2, 5)));
    Assertions.assertEquals(TestHelper.intervals(0, 1, 2, 5), insert(TestHelper.intervals(2, 5), new Interval(0, 1)));
    Assertions.assertEquals(TestHelper.intervals(0, 1, 2, 3, 4, 5), insert(TestHelper.intervals(0, 1, 4, 5), new Interval(2, 3)));
  }
}