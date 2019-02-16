package com.loic.leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UniquePathsTest {

  @Test
  void uniquePaths() {
    Assertions.assertEquals(1, UniquePaths.count(1, 1));
    Assertions.assertEquals(3, UniquePaths.count(3, 2));
    Assertions.assertEquals(28, UniquePaths.count(7, 3));
  }

  @Test
  void failed(){
    Assertions.assertEquals(1916797311, UniquePaths.count(51, 9));
  }
}