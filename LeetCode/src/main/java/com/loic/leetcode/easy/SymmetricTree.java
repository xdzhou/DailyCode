package com.loic.leetcode.easy;

import com.loic.leetcode.helper.TreeNode;

/**
 * 101. Symmetric Tree
 * https://leetcode.com/problems/symmetric-tree/
 *
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 *
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * But the following [1,2,2,null,3,null,3] is not:
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 */
public class SymmetricTree {

  public static boolean isSymmetric(TreeNode root) {
    return isSymmetric(root.left, root.right);
  }

  private static boolean isSymmetric(TreeNode node1, TreeNode node2) {
    if (node1 == null) {
      return node2 == null;
    }
    if (node2 == null) {
      return false;
    }
    return node1.val == node2.val && isSymmetric(node1.left, node2.right) && isSymmetric(
      node1.right, node2.left);
  }
}
