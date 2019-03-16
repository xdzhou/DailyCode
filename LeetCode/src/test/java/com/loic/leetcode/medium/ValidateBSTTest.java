package com.loic.leetcode.medium;

import com.loic.leetcode.helper.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ValidateBSTTest {

  @Test
  void isValidBST() {
    Assertions.assertTrue(ValidateBST.isValid(new TreeNode(Integer.MAX_VALUE)));
    Assertions.assertTrue(ValidateBST.isValid(new TreeNode(Integer.MIN_VALUE)));
    Assertions.assertTrue(ValidateBST.isValid(TreeNode.fromLevelOrder(2, 1, 3)));

    Assertions.assertFalse(ValidateBST.isValid(TreeNode.fromLevelOrder(1, 2, 3)));
  }
}