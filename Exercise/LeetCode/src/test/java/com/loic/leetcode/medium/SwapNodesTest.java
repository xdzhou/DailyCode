package com.loic.leetcode.medium;

import com.loic.helper.ListNode;
import com.loic.leetcode.medium.SwapNodes;
import com.loic.solution.SolutionChecker;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class SwapNodesTest {
  private final SolutionChecker<ListNode, ListNode> checker = SolutionChecker.create(new SwapNodes());

  @Test
  public void test() {
    checker.check(ListNode.createNodes(1, 2, 3, 4), (input, output) -> {
      Assert.assertEquals(Arrays.asList(2, 1, 4, 3), output.toList());
    }).check(ListNode.createNodes(1), (input, output) -> {
      Assert.assertEquals(Arrays.asList(1), output.toList());
    }).check(ListNode.createNodes(1, 2, 3), (input, output) -> {
      Assert.assertEquals(Arrays.asList(2, 1, 3), output.toList());
    });
  }
}