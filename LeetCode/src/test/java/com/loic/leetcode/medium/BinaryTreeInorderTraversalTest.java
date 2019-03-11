package com.loic.leetcode.medium;

import java.util.Arrays;

import com.loic.leetcode.helper.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BinaryTreeInorderTraversalTest {

  @Test
  void visit() {
    TreeNode root = new TreeNode(4);
    TreeNode left = new TreeNode(2);
    left.left = new TreeNode(1);
    left.right = new TreeNode(3);
    TreeNode right = new TreeNode(6);
    right.left = new TreeNode(5);
    right.right = new TreeNode(7);
    root.left = left;
    root.right = right;

    Assertions.assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7), BinaryTreeInorderTraversal.visit(root));
  }
}