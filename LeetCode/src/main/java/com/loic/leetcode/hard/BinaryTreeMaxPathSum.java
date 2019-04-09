package com.loic.leetcode.hard;

import com.loic.leetcode.helper.TreeNode;

/**
 * 124. Binary Tree Maximum Path Sum
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
 *
 * Given a non-empty binary tree, find the maximum path sum.
 *
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.
 *
 * Example 1:
 *
 * Input: [1,2,3]
 *
 *        1
 *       / \
 *      2   3
 *
 * Output: 6
 * Example 2:
 *
 * Input: [-10,9,20,null,null,15,7]
 *
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * Output: 42
 */
public class BinaryTreeMaxPathSum {

  public static int maxPathSum(TreeNode root) {
    int[] max = new int[]{Integer.MIN_VALUE, 0};
    maxPathSum(max, root);
    return max[0];
  }

  /**
   * @param max output: max[0] is the maxPathSum of the node, max[1] is the maxPathSum for witch the
   * the path ends at root node
   * @param root input
   */
  private static void maxPathSum(int[] max, TreeNode root) {
    // the maxPathSum of left node for witch the path end at left node
    int leftMax = 0;
    if (root.left != null) {
      maxPathSum(max, root.left);
      leftMax = Math.max(0, max[1]);
    }
    // the maxPathSum of right node for witch the path end at right node
    int rightMax = 0;
    if (root.right != null) {
      maxPathSum(max, root.right);
      rightMax = Math.max(0, max[1]);
    }
    // the maxPathSum of this root node is maximum of
    // the maxPathSum of this left node, the maxPathSum of this right node,
    // and the path pass root node
    max[0] = Math.max(leftMax + rightMax + root.val, max[0]);
    max[1] = root.val + Math.max(leftMax, rightMax);
  }
}
