package com.loic.leetcode.medium;

import static com.loic.leetcode.TestHelper.toArray;

import java.util.Arrays;

import com.loic.leetcode.helper.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ConstructBTFromInPostOrderTraversalTest {

  @Test
  void buildTree() {
    TreeNode expected = TreeNode.fromLevelOrder(Arrays.asList(3, 9, 20, null, null, 15, 7));
    TreeNode result = ConstructBTFromInPostOrderTraversal.buildTree(toArray(9, 3, 15, 20, 7), toArray(9, 15, 7, 20, 3));
    Assertions.assertTrue(expected.equal(result));
  }

  @Test
  void empty() {
    Assertions.assertNull(ConstructBTFromInPostOrderTraversal.buildTree(toArray(), toArray()));
  }

  @Test
  void singleValue() {
    TreeNode expected = TreeNode.fromLevelOrder(1);
    TreeNode result = ConstructBTFromInPostOrderTraversal.buildTree(toArray(1), toArray(1));
    Assertions.assertTrue(expected.equal(result));
  }
}