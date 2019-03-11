package com.loic.leetcode.medium;

import static com.loic.leetcode.helper.ListNode.create;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ReverseLinkedList2Test {

  @Test
  void reverseBetween() {
    Assertions.assertTrue(create(1, 2, 3).equalTo(ReverseLinkedList2.reverseBetween(create(1, 2, 3), 2, 2)));
    Assertions.assertTrue(create(1, 4, 3, 2, 5).equalTo(ReverseLinkedList2.reverseBetween(create(1, 2, 3, 4, 5), 2, 4)));
  }
}