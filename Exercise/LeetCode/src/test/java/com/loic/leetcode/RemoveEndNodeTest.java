package com.loic.leetcode;

import com.loic.helper.ListNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
    ListNode node = new RemoveEndNode().resolve(ListNode.createNodes(nums), n);
    List<Integer> result1 = node == null ? Collections.EMPTY_LIST : node.toList();
    node = new RemoveEndNode().removeNthFromEnd(ListNode.createNodes(nums), n);
    List<Integer> result2 = node == null ? Collections.EMPTY_LIST : node.toList();
    Assert.assertEquals(expect, result1);
    Assert.assertEquals(expect, result2);
  }
}