package com.loic.leetcode.medium;

import com.loic.leetcode.helper.TreeNode;

/**
 * 156.Binary Tree Upside Down
 * <p>
 * Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.
 * <p>
 * Example:
 * <p>
 * Input: [1,2,3,4,5]
 * <p>
 * 1
 * / \
 * 2   3
 * / \
 * 4   5
 * <p>
 * Output: return the root of the binary tree [4,5,2,#,#,3,1]
 * <p>
 * 4
 * / \
 * 5   2
 * / \
 * 3   1
 */
public class BinaryTreeUpsideDown {

  public static TreeNode upsideDown(TreeNode root) {
    if (root == null || (root.left == null && root.right == null)) {
      return root;
    }
    TreeNode result = upsideDown(root.left);
    root.left.left = root.right;
    root.left.right = root;
    root.left = root.right = null;
    return result;
  }
}
