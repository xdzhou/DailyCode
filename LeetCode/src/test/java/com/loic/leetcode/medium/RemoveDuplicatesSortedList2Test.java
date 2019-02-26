package com.loic.leetcode.medium;

import com.loic.leetcode.helper.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RemoveDuplicatesSortedList2Test {

  @Test
  void deleteDuplicates() {
    Assertions.assertNull(RemoveDuplicatesSortedList2.deleteDuplicates(null));
    Assertions.assertTrue(ListNode.create(1, 2, 5).equalTo(RemoveDuplicatesSortedList2.deleteDuplicates(ListNode.create(1, 2, 3, 3, 4, 4, 5))));
  }
}