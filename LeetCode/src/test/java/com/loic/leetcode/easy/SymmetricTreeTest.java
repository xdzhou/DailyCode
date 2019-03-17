package com.loic.leetcode.easy;

import java.util.Arrays;

import com.loic.leetcode.helper.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SymmetricTreeTest {

  @Test
  void isSymmetric() {
    Assertions.assertTrue(SymmetricTree.isSymmetric(TreeNode.fromLevelOrder(1, 2, 2, 3, 4, 4, 3)));
    Assertions.assertFalse(SymmetricTree.isSymmetric(TreeNode.fromLevelOrder(Arrays.asList(1, 2, 2, null, 3, null, 3))));
  }
}