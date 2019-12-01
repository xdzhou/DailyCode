package com.loic.leetcode.medium;

import com.loic.leetcode.helper.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LinkedListCycle2Test {

  @Test
  void noCycle() {
    Assertions.assertNull(LinkedListCycle2.detectCycle(null));
    Assertions.assertNull(LinkedListCycle2.detectCycle(new ListNode(1)));
  }

  @Test
  void hasCycle() {
    ListNode head = ListNode.create(3, 2, 0, 4);
    head.next.next.next.next = head.next;
    Assertions.assertEquals(head.next, LinkedListCycle2.detectCycle(head));
  }

  @Test
  void ACycle() {
    ListNode head = ListNode.create(3, 2, 0, 4);
    head.next.next.next.next = head;
    Assertions.assertEquals(head, LinkedListCycle2.detectCycle(head));
  }
}