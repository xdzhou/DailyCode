package com.loic.leetcode.medium;

import java.util.Arrays;
import java.util.Collections;

import com.loic.helper.ListNode;
import com.loic.leetcode.SolutionChecker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SwapNodesTest {
  private final SolutionChecker<ListNode, ListNode> checker = SolutionChecker.create(SwapNodes::resolve);

  @Test
  void test() {
    checker.check(ListNode.createNodes(1, 2, 3, 4), (input, output) -> {
      Assertions.assertEquals(Arrays.asList(2, 1, 4, 3), output.toList());
    }).check(ListNode.createNodes(1), (input, output) -> {
      Assertions.assertEquals(Collections.singletonList(1), output.toList());
    }).check(ListNode.createNodes(1, 2, 3), (input, output) -> {
      Assertions.assertEquals(Arrays.asList(2, 1, 3), output.toList());
    });
  }
}