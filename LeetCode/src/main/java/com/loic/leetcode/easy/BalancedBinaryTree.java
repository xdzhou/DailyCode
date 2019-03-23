package com.loic.leetcode.easy;

import com.loic.leetcode.helper.TreeNode;

/**
 * 110. Balanced Binary Tree
 * https://leetcode.com/problems/balanced-binary-tree/
 *
 * Given a binary tree, determine if it is height-balanced.
 *
 * For this problem, a height-balanced binary tree is defined as:
 *
 * a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 *
 * Example 1:
 *
 * Given the following tree [3,9,20,null,null,15,7]:
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * Return true.
 */
public class BalancedBinaryTree {

  public static boolean isBalanced(TreeNode root) {
    return maxDepth(root) != -1;
  }

  /**
   * return max depth of the node,
   * return -1 if the depth of two subtree differ by more than 1
   */
  private static int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int leftResult = maxDepth(root.left);
    if (leftResult < 0) {
      return leftResult;
    }
    int rightResult = maxDepth(root.right);
    if (rightResult < 0) {
      return rightResult;
    }
    int delta = Math.abs(rightResult - leftResult);
    if (delta > 1) {
      return -1;
    } else {
      return 1 + Math.max(leftResult, rightResult);
    }
  }
}
