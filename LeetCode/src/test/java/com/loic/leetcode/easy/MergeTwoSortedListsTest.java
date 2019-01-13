package com.loic.leetcode.easy;

import com.loic.leetcode.helper.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MergeTwoSortedListsTest {

  @Test
  void merge() {
    testCase(ListNode.create(0), ListNode.create(1, 2, 3), ListNode.create(0, 1, 2, 3));
    testCase(ListNode.create(1, 3, 4), ListNode.create(1, 2, 4), ListNode.create(1, 1, 2, 3, 4, 4));
  }

  private void testCase(ListNode l1, ListNode l2, ListNode merged) {
    Assertions.assertTrue(merged.equalTo(MergeTwoSortedLists.merge(l1, l2)));
  }
}