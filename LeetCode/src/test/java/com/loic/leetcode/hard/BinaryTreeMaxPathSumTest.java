package com.loic.leetcode.hard;

import java.util.Arrays;

import com.loic.leetcode.helper.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BinaryTreeMaxPathSumTest {

  @Test
  void test() {
    Assertions.assertEquals(6, BinaryTreeMaxPathSum.maxPathSum(TreeNode.fromLevelOrder(1, 2, 3)));
    Assertions.assertEquals(42, BinaryTreeMaxPathSum.maxPathSum(TreeNode.fromLevelOrder(Arrays.asList(-10, 9, 20, null, null, 15, 7))));
  }
}