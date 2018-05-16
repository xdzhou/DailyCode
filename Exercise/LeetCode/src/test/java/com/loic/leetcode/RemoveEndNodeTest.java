package com.loic.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RemoveEndNodeTest {

  @Test
  public void testNormalRemove() {
    test(2, Arrays.asList(1, 2, 3, 5), 1, 2, 3, 4, 5);
  }

  @Test
  public void testLastOne() {
    test(1, Arrays.asList(1, 2), 1, 2, 3);
  }

  @Test
  public void testFirstOne() {
    test(2, Arrays.asList(2), 1, 2);
  }

  @Test
  public void testFirstOneWithSizeOne() {
    test(1, Arrays.asList(), 1);
  }

  @Test
  public void testSecondOne() {
    test(2, Arrays.asList(1, 3), 1, 2, 3);
  }

  private void test(int n, List<Integer> expect, int... nums) {
    List<Integer> result1 = toList(new RemoveEndNode().resolve(createNodes(nums), n));
    List<Integer> result2 = toList(new RemoveEndNode().removeNthFromEnd(createNodes(nums), n));
    Assert.assertEquals(expect, result1);
    Assert.assertEquals(expect, result2);
  }

  private RemoveEndNode.ListNode createNodes(int... nums) {
    RemoveEndNode.ListNode head = null;
    RemoveEndNode.ListNode cur = null;
    for (int num : nums) {
      RemoveEndNode.ListNode node = new RemoveEndNode.ListNode(num);
      if (cur == null) {
        head = node;
      } else {
        cur.next = node;
      }
      cur = node;
    }
    return head;
  }

  private List<Integer> toList(RemoveEndNode.ListNode node) {
    List<Integer> list = new ArrayList<>();
    while (node != null) {
      list.add(node.val);
      node = node.next;
    }
    return list;
  }
}