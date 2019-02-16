package com.loic.leetcode.medium;

import com.loic.leetcode.helper.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RotateListTest {

  @Test
  void rotateRight() {
    ListNode head = ListNode.create(1, 2, 3, 4, 5);
    Assertions.assertTrue(head.equalTo(RotateList.rotateRight(head, 0)));
    Assertions.assertTrue(head.equalTo(RotateList.rotateRight(head, 5)));

    Assertions.assertTrue(ListNode.create(4, 5, 1, 2, 3).equalTo(RotateList.rotateRight(head, 2)));
  }

  @Test
  void empty(){
    Assertions.assertNull(RotateList.rotateRight(ListNode.create(), 2));
  }
}