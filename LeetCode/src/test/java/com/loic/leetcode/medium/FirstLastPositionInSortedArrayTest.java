package com.loic.leetcode.medium;

import static com.loic.leetcode.TestHelper.toArray;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class FirstLastPositionInSortedArrayTest {

  @Test
  void find() {
    assertArrayEquals(toArray(0, 0), FirstLastPositionInSortedArray.find(0, 0, 1, 2, 3));
    assertArrayEquals(toArray(0, 3), FirstLastPositionInSortedArray.find(0, 0, 0, 0, 0));
    assertArrayEquals(toArray(-1, -1), FirstLastPositionInSortedArray.find(0));

    assertArrayEquals(toArray(3, 4), FirstLastPositionInSortedArray.find(8, 5, 7, 7, 8, 8, 10));
    assertArrayEquals(toArray(-1, -1), FirstLastPositionInSortedArray.find(6, 5, 7, 7, 8, 8, 10));
  }
}