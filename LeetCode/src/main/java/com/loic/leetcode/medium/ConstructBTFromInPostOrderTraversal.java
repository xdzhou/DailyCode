package com.loic.leetcode.medium;

import com.loic.leetcode.helper.TreeNode;

/**
 * 106. Construct Binary Tree from Inorder and Postorder Traversal
 * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 *
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 *
 * Note:
 * You may assume that duplicates do not exist in the tree.
 *
 * For example, given
 *
 * inorder = [9,3,15,20,7]
 * postorder = [9,15,7,20,3]
 * Return the following binary tree:
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class ConstructBTFromInPostOrderTraversal {

  public static TreeNode buildTree(int[] inorder, int[] postorder) {
    return buildTree(inorder, 0, postorder, 0, inorder.length);
  }

  private static TreeNode buildTree(int[] inorder, int fromIn, int[] postorder, int fromPost, int len) {
    if (len == 0) {
      return null;
    } else if (len == 1) {
      return new TreeNode(inorder[fromIn]);
    }
    int rootVal = postorder[fromPost + len - 1];
    TreeNode root = new TreeNode(rootVal);
    int rootIndex = fromIn;
    while (inorder[rootIndex] != rootVal) {
      rootIndex++;
    }
    int leftLen = rootIndex - fromIn;
    root.left = buildTree(inorder, fromIn, postorder, fromPost, leftLen);
    root.right = buildTree(inorder, rootIndex + 1, postorder, fromPost + leftLen, len - 1 - leftLen);
    return root;
  }
}
