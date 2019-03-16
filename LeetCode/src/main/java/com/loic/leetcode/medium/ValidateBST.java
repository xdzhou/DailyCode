package com.loic.leetcode.medium;

import com.loic.leetcode.helper.TreeNode;

/**
 * 98. Validate Binary Search Tree
 * https://leetcode.com/problems/validate-binary-search-tree/
 *
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 *
 * Assume a BST is defined as follows:
 *
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * Example 1:
 *
 * Input:
 *     2
 *    / \
 *   1   3
 * Output: true
 * Example 2:
 *
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * Output: false
 */
public class ValidateBST {

  public static boolean isValid(TreeNode root) {
    //min & max can't use INT
    return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
  }

  private static boolean isValid(TreeNode root, long min, long max) {
    if (root == null) {
      return true;
    } else if (root.val > min && root.val < max) {
      return isValid(root.left, min, root.val) && isValid(root.right, root.val, max);
    } else {
      return false;
    }
  }
}
