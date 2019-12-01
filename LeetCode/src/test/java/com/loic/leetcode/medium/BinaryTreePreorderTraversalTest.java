package com.loic.leetcode.medium;

import java.util.Arrays;

import com.loic.leetcode.helper.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BinaryTreePreorderTraversalTest {

  @Test
  void preorderTraversal() {
    Assertions.assertEquals(
      Arrays.asList(1, 2, 4, 5, 3, 6, 7),
      BinaryTreePreorderTraversal.preorderTraversal(TreeNode.fromLevelOrder(1, 2, 3, 4, 5, 6, 7)));
  }
}