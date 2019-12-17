package com.loic.leetcode.medium;

import static com.loic.leetcode.medium.KthLargestElement.findKthLargest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class KthLargestElementTest {

  @Test
  void simple() {
    Assertions.assertEquals(0, findKthLargest(1, 0, 0, 0));
    Assertions.assertEquals(0, findKthLargest(2, 0, 0, 0));
    Assertions.assertEquals(0, findKthLargest(3, 0, 0, 0, 0));
    Assertions.assertEquals(5, findKthLargest(1, 5));
    Assertions.assertEquals(5, findKthLargest(2, 3, 2, 1, 5, 6, 4));
    Assertions.assertEquals(4, findKthLargest(4, 3, 2, 3, 1, 2, 4, 5, 5, 6));
  }
}