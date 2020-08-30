package com.loic.leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FindDuplicateNumberTest {

  @Test
  void findDuplicate() {
    Assertions.assertEquals(FindDuplicateNumber.findDuplicate(1, 3, 4, 2, 2), 2);
    Assertions.assertEquals(FindDuplicateNumber.findDuplicate(3, 1, 3, 4, 2), 3);
  }
}