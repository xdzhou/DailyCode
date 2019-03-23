package com.loic.leetcode.easy;

import java.util.Arrays;

import com.loic.leetcode.helper.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BalancedBinaryTreeTest {

  @Test
  void isBalanced() {
    Assertions.assertTrue(BalancedBinaryTree.isBalanced(TreeNode.fromLevelOrder(Arrays.asList(3, 9, 20, null, null, 15, 7))));
    Assertions.assertFalse(BalancedBinaryTree.isBalanced(TreeNode.fromLevelOrder(Arrays.asList(1, 2, 2, 3, 3, null, null, 4, 4))));

  }
}