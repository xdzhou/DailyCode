package com.loic.leetcode.medium;

import static com.loic.leetcode.helper.ListNode.create;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PartitionListTest {

  @Test
  void partition() {
    assertTrue(create(1, 2).equalTo(PartitionList.partition(create(2, 1), 2)));
    assertTrue(create(1, 2, 2, 4, 3, 5).equalTo(PartitionList.partition(create(1, 4, 3, 2, 5, 2), 3)));
  }

  @Test
  void boundary() {
    assertTrue(create(1).equalTo(PartitionList.partition(create(1), 3)));
    assertNull(PartitionList.partition(create(), 3));
  }
}