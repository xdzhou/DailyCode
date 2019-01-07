package com.loic.leetcode.medium;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.loic.helper.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RemoveEndNodeTest {

  @Test
  void testNormalRemove() {
    test(2, Arrays.asList(1, 2, 3, 5), 1, 2, 3, 4, 5);
    test(200, Arrays.asList(1, 2, 3, 5), 1, 2, 3, 5);
  }

  @Test
  void testLastOne() {
    test(1, Arrays.asList(1, 2), 1, 2, 3);
  }

  @Test
  void testFirstOne() {
    test(2, Arrays.asList(2), 1, 2);
  }

  @Test
  void testFirstOneWithSizeOne() {
    test(1, Arrays.asList(), 1);
  }

  @Test
  void testSecondOne() {
    test(2, Arrays.asList(1, 3), 1, 2, 3);
  }

  private void test(int n, List<Integer> expect, int... nums) {
    ListNode node = RemoveEndNode.resolve(ListNode.createNodes(nums), n);
    List<Integer> result1 = node == null ? Collections.EMPTY_LIST : node.toList();
    node = RemoveEndNode.removeNthFromEnd(ListNode.createNodes(nums), n);
    List<Integer> result2 = node == null ? Collections.EMPTY_LIST : node.toList();
    Assertions.assertEquals(expect, result1);
    Assertions.assertEquals(expect, result2);
  }
}