package com.loic.leetcode.hard;

import java.util.Arrays;

import com.loic.leetcode.helper.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BinaryTreePostorderTraversalTest {

  @Test
  void postorderTraversal() {
    Assertions.assertEquals(
      Arrays.asList(4, 5, 2, 6, 7, 3, 1),
      BinaryTreePostorderTraversal.postorderTraversal(TreeNode.fromLevelOrder(1, 2, 3, 4, 5, 6, 7))
    );
  }
}