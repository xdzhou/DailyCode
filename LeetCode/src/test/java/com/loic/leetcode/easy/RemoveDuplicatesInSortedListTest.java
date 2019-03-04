package com.loic.leetcode.easy;

import com.loic.leetcode.helper.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RemoveDuplicatesInSortedListTest {

  @Test
  void delete() {
    Assertions.assertNull(RemoveDuplicatesInSortedList.delete(ListNode.create()));
    Assertions.assertTrue(ListNode.create(1, 2).equalTo(RemoveDuplicatesInSortedList.delete(ListNode.create(1, 1, 2))));
  }
}