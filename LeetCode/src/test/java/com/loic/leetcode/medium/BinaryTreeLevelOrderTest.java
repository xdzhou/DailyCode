package com.loic.leetcode.medium;

import java.util.Arrays;
import java.util.List;

import com.loic.leetcode.helper.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BinaryTreeLevelOrderTest {

  @Test
  void levelOrder() {
    TreeNode root = TreeNode.fromLevelOrder(Arrays.asList(3, 9, 20, null, null, 15, 7));
    List<List<Integer>> expect = Arrays.asList(
      Arrays.asList(3),
      Arrays.asList(9, 20),
      Arrays.asList(15, 7)
    );
    Assertions.assertEquals(expect, BinaryTreeLevelOrder.levelOrder(root));
  }
}