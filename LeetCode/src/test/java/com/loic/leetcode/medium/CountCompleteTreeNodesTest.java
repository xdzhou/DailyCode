package com.loic.leetcode.medium;

import java.util.stream.IntStream;

import com.loic.leetcode.helper.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CountCompleteTreeNodesTest {

  @Test
  void simple() {
    Assertions.assertEquals(0, CountCompleteTreeNodes.countNodes(null));
    for (int i = 1; i < 1000; i++) {
      check(i);
    }
  }

  private void check(int len) {
    TreeNode node = TreeNode.fromLevelOrder(IntStream.range(0, len).toArray());
    Assertions.assertEquals(len, CountCompleteTreeNodes.countNodes(node));
  }
}