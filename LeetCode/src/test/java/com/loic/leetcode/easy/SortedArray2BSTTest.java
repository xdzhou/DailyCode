package com.loic.leetcode.easy;

import java.util.Arrays;

import com.loic.leetcode.helper.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SortedArray2BSTTest {

  @Test
  void toBST() {
    TreeNode expected = TreeNode.fromLevelOrder(Arrays.asList(0, -3, 9, -10, null, 5));
    TreeNode result = SortedArray2BST.toBST(-10, -3, 0, 5, 9);
    Assertions.assertTrue(expected.equal(result));
  }

  @Test
  void empty() {
    Assertions.assertNull(SortedArray2BST.toBST());
  }

  @Test
  void singleValue() {
    TreeNode expected = TreeNode.fromLevelOrder(1);
    TreeNode result = SortedArray2BST.toBST(1);
    Assertions.assertTrue(expected.equal(result));
  }
}