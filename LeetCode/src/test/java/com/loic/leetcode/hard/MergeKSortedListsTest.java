package com.loic.leetcode.hard;

import com.loic.helper.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MergeKSortedListsTest {

  @Test
  void empty() {
    Assertions.assertNull(MergeKSortedLists.merge());
    Assertions.assertNull(MergeKSortedLists.merge(new ListNode[]{null}));
    Assertions.assertNull(MergeKSortedLists.merge(null, null));
  }

  @Test
  void singleListNode() {
    ListNode node = ListNode.create(1, 2, 3);
    Assertions.assertTrue(node.equalTo(MergeKSortedLists.merge(node)));
  }

  @Test
  void merge() {
    ListNode l1 = ListNode.create(1, 4, 5);
    ListNode l2 = ListNode.create(1, 3, 4);
    ListNode l3 = ListNode.create(2, 6);
    ListNode expected = ListNode.create(1, 1, 2, 3, 4, 4, 5, 6);
    Assertions.assertTrue(expected.equalTo(MergeKSortedLists.merge(l1, l2, l3)));
  }

}