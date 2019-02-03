package com.loic.leetcode.hard;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NQueensTest {

  @Test
  void solve1() {
    Assertions.assertEquals(Collections.singletonList(Collections.singletonList("Q")), NQueens.solve(1));
  }

  @Test
  void solve4() {
    Assertions.assertEquals(Arrays.asList(
      Arrays.asList(".Q..", "...Q", "Q...", "..Q."),
      Arrays.asList("..Q.", "Q...", "...Q", ".Q..")),
      NQueens.solve(4));
  }
}