package com.loic.leetcode.easy;

import java.util.Arrays;
import java.util.List;

import com.loic.leetcode.helper.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BinaryTreeLevelOrder2Test {

  @Test
  void levelOrderBottom() {
    List<List<Integer>> expected = Arrays.asList(
      Arrays.asList(15, 7),
      Arrays.asList(9, 20),
      Arrays.asList(3)
    );
    Assertions.assertEquals(expected, BinaryTreeLevelOrder2.levelOrderBottom(TreeNode.fromLevelOrder(Arrays.asList(3, 9, 20, null, null, 15, 7))));
  }
}