package com.loic.leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SearchInRotatedSortedArrayTest {

  @Test
  void search() {
    Assertions.assertEquals(0, SearchInRotatedSortedArray.search(0, 0, 1, 2, 3));
    Assertions.assertEquals(1, SearchInRotatedSortedArray.search(1, 0, 1, 2, 3));
    Assertions.assertEquals(2, SearchInRotatedSortedArray.search(2, 0, 1, 2, 3));
    Assertions.assertEquals(3, SearchInRotatedSortedArray.search(3, 0, 1, 2, 3));

    Assertions.assertEquals(0, SearchInRotatedSortedArray.search(4, 4, 5, 6, 1, 2, 3));
    Assertions.assertEquals(4, SearchInRotatedSortedArray.search(0, 4, 5, 6, 7, 0, 1, 2));
    Assertions.assertEquals(-1, SearchInRotatedSortedArray.search(3, 4, 5, 6, 7, 0, 1, 2));
  }
}