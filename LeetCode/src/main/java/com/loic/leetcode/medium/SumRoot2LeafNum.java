package com.loic.leetcode.medium;

import com.loic.leetcode.helper.TreeNode;

/**
 * 129. Sum Root to Leaf Numbers
 * https://leetcode.com/problems/sum-root-to-leaf-numbers/
 *
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 *
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 *
 * Find the total sum of all root-to-leaf numbers.
 *
 * Note: A leaf is a node with no children.
 *
 * Example:
 *
 * Input: [1,2,3]
 *     1
 *    / \
 *   2   3
 * Output: 25
 * Explanation:
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * Therefore, sum = 12 + 13 = 25.
 */
public class SumRoot2LeafNum {

  public static int sum(TreeNode root) {
    int[] sum = new int[1];
    if (root != null) {
      process(sum, 0, root);
    }
    return sum[0];
  }

  private static void process(int[] sum, int num, TreeNode node) {
    int val = num * 10 + node.val;
    if (node.left == null && node.right == null) {
      sum[0] += val;
    } else {
      if (node.left != null) {
        process(sum, val, node.left);
      }
      if (node.right != null) {
        process(sum, val, node.right);
      }
    }
  }
}
