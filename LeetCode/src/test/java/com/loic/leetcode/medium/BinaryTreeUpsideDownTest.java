package com.loic.leetcode.medium;

import java.util.Arrays;

import com.loic.leetcode.helper.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BinaryTreeUpsideDownTest {

  @Test
  void simple() {
    TreeNode singleNode = TreeNode.fromLevelOrder(1);
    Assertions.assertTrue(singleNode.equal(BinaryTreeUpsideDown.upsideDown(singleNode)));

    TreeNode actual = BinaryTreeUpsideDown.upsideDown(TreeNode.fromLevelOrder(1, 2, 3, 4, 5));
    TreeNode expected = TreeNode.fromLevelOrder(Arrays.asList(4, 5, 2, null, null, 3, 1));
    Assertions.assertTrue(actual.equal(expected));
  }
}