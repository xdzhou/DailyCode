package com.loic.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RemoveEndNodeTest {

  @Test
  public void testNormalRemove() {
    List<Integer> result = toList(new RemoveEndNode().resolve(createNodes(1, 2, 3, 4, 5), 2));
    Assert.assertEquals(Arrays.asList(1, 2, 3, 5), result);
  }

  @Test
  public void testBigN() {
    List<Integer> result = toList(new RemoveEndNode().resolve(createNodes(1, 2, 3, 4, 5), 200));
    Assert.assertEquals(Arrays.asList(1, 2, 3, 4, 5), result);
  }

  @Test
  public void testLastOne() {
    List<Integer> result = toList(new RemoveEndNode().resolve(createNodes(1, 2, 3), 1));
    Assert.assertEquals(Arrays.asList(1, 2), result);
  }

  @Test
  public void testFirstOne() {
    List<Integer> result = toList(new RemoveEndNode().resolve(createNodes(1, 2), 2));
    Assert.assertEquals(Arrays.asList(2), result);
  }

  @Test
  public void testFirstOneWithSizeOne() {
    List<Integer> result = toList(new RemoveEndNode().resolve(createNodes(1), 1));
    Assert.assertEquals(Arrays.asList(), result);
  }

  @Test
  public void testSecondOne() {
    List<Integer> result = toList(new RemoveEndNode().resolve(createNodes(1, 2, 3), 2));
    Assert.assertEquals(Arrays.asList(1, 3), result);
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