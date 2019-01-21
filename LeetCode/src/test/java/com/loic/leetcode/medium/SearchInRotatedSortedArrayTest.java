package com.loic.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SearchInRotatedSortedArrayTest {

  @Test
  void search() {
    assertEquals(0, SearchInRotatedSortedArray.search(0, 0, 1, 2, 3));
    assertEquals(1, SearchInRotatedSortedArray.search(1, 0, 1, 2, 3));
    assertEquals(2, SearchInRotatedSortedArray.search(2, 0, 1, 2, 3));
    assertEquals(3, SearchInRotatedSortedArray.search(3, 0, 1, 2, 3));

    assertEquals(0, SearchInRotatedSortedArray.search(4, 4, 5, 6, 1, 2, 3));
    assertEquals(4, SearchInRotatedSortedArray.search(0, 4, 5, 6, 7, 0, 1, 2));
    assertEquals(-1, SearchInRotatedSortedArray.search(3, 4, 5, 6, 7, 0, 1, 2));
  }

  @Test
  void search2() {
    assertEquals(0, SearchInRotatedSortedArray.search2(0, 0, 1, 2, 3));
    assertEquals(1, SearchInRotatedSortedArray.search2(1, 0, 1, 2, 3));
    assertEquals(2, SearchInRotatedSortedArray.search2(2, 0, 1, 2, 3));
    assertEquals(3, SearchInRotatedSortedArray.search2(3, 0, 1, 2, 3));

    assertEquals(0, SearchInRotatedSortedArray.search2(4, 4, 5, 6, 1, 2, 3));
    assertEquals(4, SearchInRotatedSortedArray.search2(0, 4, 5, 6, 7, 0, 1, 2));
    assertEquals(-1, SearchInRotatedSortedArray.search2(3, 4, 5, 6, 7, 0, 1, 2));
  }
}