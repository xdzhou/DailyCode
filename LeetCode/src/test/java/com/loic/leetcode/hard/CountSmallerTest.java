package com.loic.leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

class CountSmallerTest {

  @Test
  void countSmaller() {
    Assertions.assertEquals(Arrays.asList(2, 1, 1, 0), CountSmaller.countSmaller(5, 2, 6, 1));
    Assertions.assertEquals(Arrays.asList(0, 0, 0, 0), CountSmaller.countSmaller(1, 1, 1, 1));
    Assertions.assertEquals(Arrays.asList(3, 2, 1, 0), CountSmaller.countSmaller(4, 3, 2, 1));
    Assertions.assertEquals(Arrays.asList(0), CountSmaller.countSmaller(1));
    Assertions.assertEquals(Collections.emptyList(), CountSmaller.countSmaller());
  }
}