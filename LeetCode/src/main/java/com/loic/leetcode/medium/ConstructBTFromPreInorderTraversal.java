package com.loic.leetcode.medium;

import com.loic.leetcode.helper.TreeNode;

/**
 * 105. Construct Binary Tree from Preorder and Inorder Traversal
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 *
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 *
 * Note:
 * You may assume that duplicates do not exist in the tree.
 *
 * For example, given
 *
 * preorder = [3,9,20,15,7]
 * inorder = [9,3,15,20,7]
 * Return the following binary tree:
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class ConstructBTFromPreInorderTraversal {

  public static TreeNode buildTree(int[] preorder, int[] inorder) {
    return buildTree(preorder, 0, inorder, 0, preorder.length);
  }

  private static TreeNode buildTree(int[] preorder, int from1, int[] inorder, int from2, int len) {
    if (len == 0) {
      return null;
    }
    TreeNode root = new TreeNode(preorder[from1]);
    if (len > 1) {
      int index = from2;
      while (inorder[index] != root.val) {
        index++;
      }
      int leftLen = index - from2;
      root.left = buildTree(preorder, from1 + 1, inorder, from2, index - from2);
      root.right = buildTree(preorder, from1 + 1 + leftLen, inorder, from2 + 1 + leftLen, len - 1 - leftLen);
    }
    return root;
  }
}
