package com.loic.leetcode.medium;

import com.loic.helper.ListNode;
import com.loic.solution.SolutionChecker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class SwapNodesTest {
  private final SolutionChecker<ListNode, ListNode> checker = SolutionChecker.create(new SwapNodes());

  @Test
  void test() {
    checker.check(ListNode.createNodes(1, 2, 3, 4), (input, output) -> {
      Assertions.assertEquals(Arrays.asList(2, 1, 4, 3), output.toList());
    }).check(ListNode.createNodes(1), (input, output) -> {
      Assertions.assertEquals(Arrays.asList(1), output.toList());
    }).check(ListNode.createNodes(1, 2, 3), (input, output) -> {
      Assertions.assertEquals(Arrays.asList(2, 1, 3), output.toList());
    });
  }
}