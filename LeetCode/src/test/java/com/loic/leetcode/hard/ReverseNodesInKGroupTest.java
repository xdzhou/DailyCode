package com.loic.leetcode.hard;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.loic.leetcode.helper.ListNode;
import org.junit.jupiter.api.Test;

class ReverseNodesInKGroupTest {

  @Test
  void reverseK() {
    assertTrue(ListNode.create(1, 2, 3, 4).equalTo(ReverseNodesInKGroup.reverseK(ListNode.create(1, 2, 3, 4), 1)));
    assertTrue(ListNode.create(2, 1, 4, 3).equalTo(ReverseNodesInKGroup.reverseK(ListNode.create(1, 2, 3, 4), 2)));
    assertTrue(ListNode.create(3, 2, 1, 4).equalTo(ReverseNodesInKGroup.reverseK(ListNode.create(1, 2, 3, 4), 3)));
    assertTrue(ListNode.create(4, 3, 2, 1).equalTo(ReverseNodesInKGroup.reverseK(ListNode.create(1, 2, 3, 4), 4)));
    assertTrue(ListNode.create(1, 2, 3, 4).equalTo(ReverseNodesInKGroup.reverseK(ListNode.create(1, 2, 3, 4), 5)));
  }
}