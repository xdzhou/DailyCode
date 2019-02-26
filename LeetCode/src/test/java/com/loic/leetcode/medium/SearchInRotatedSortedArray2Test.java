package com.loic.leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SearchInRotatedSortedArray2Test {

  @Test
  void search() {
    Assertions.assertTrue(SearchInRotatedSortedArray2.search(6, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 6));
    Assertions.assertTrue(SearchInRotatedSortedArray2.search(6, 2, 6, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2));
    Assertions.assertTrue(SearchInRotatedSortedArray2.search(6, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 6, 2));

    Assertions.assertFalse(SearchInRotatedSortedArray2.search(6));
    Assertions.assertFalse(SearchInRotatedSortedArray2.search(6, 2));
    Assertions.assertTrue(SearchInRotatedSortedArray2.search(6, 6, 1));
  }
}