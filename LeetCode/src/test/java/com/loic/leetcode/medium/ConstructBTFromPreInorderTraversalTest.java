package com.loic.leetcode.medium;

import static com.loic.leetcode.TestHelper.toArray;
import static com.loic.leetcode.medium.ConstructBTFromPreInorderTraversal.buildTree;

import java.util.Arrays;

import com.loic.leetcode.helper.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ConstructBTFromPreInorderTraversalTest {

  @Test
  void emptyTree() {
    Assertions.assertNull(buildTree(toArray(), toArray()));
  }

  @Test
  void build() {
    Assertions.assertTrue(new TreeNode(1).equal(buildTree(toArray(1), toArray(1))));

    Assertions.assertTrue(TreeNode.fromLevelOrder(Arrays.asList(3, 9, 20, null, null, 15, 7))
      .equal(buildTree(toArray(3, 9, 20, 15, 7), toArray(9, 3, 15, 20, 7))));

    Assertions.assertTrue(TreeNode.fromLevelOrder(Arrays.asList(1, null, 2))
      .equal(buildTree(toArray(1, 2), toArray(1, 2))));
  }
}