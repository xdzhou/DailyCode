package com.loic.leetcode.hard;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.loic.leetcode.helper.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class VerticalOrderTraversalBinaryTreeTest {
  private final VerticalOrderTraversalBinaryTree solution = new VerticalOrderTraversalBinaryTree();

  @Test
  void resolve() {
    assertTrue(solution.resolve(TreeNode.fromLevelOrder()).isEmpty());

    assertEquals(asList(asList(9), asList(3, 15), asList(20), asList(7)),
      solution.resolve(TreeNode.fromLevelOrder(asList(3, 9, 20, null, null, 15, 7))));

    assertEquals(asList(asList(4), asList(2), asList(1,5,6), asList(3), asList(7)),
      solution.resolve(TreeNode.fromLevelOrder(1,2,3,4,5,6,7)));
  }
}