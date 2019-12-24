package com.loic.leetcode.medium;

import com.loic.leetcode.helper.TreeNode;

/**
 * 222. Count Complete Tree Nodes
 * <p>
 * Given a complete binary tree, count the number of nodes.
 * <p>
 * Note:
 * <p>
 * Definition of a complete binary tree from Wikipedia:
 * In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 * <p>
 * Example:
 * <p>
 * Input:
 * *    1
 * *   / \
 * *  2   3
 * * / \  /
 * *4  5 6
 * <p>
 * Output: 6
 */
public class CountCompleteTreeNodes {

  public static int countNodes(TreeNode root) {
    int depth = -1;
    TreeNode node = root;
    while (node != null) {
      depth++;
      node = node.left;
    }
    if (depth < 1) {
      return depth + 1;
    }
    //binary search
    int left = 0, right = (int) Math.pow(2, depth) - 1;
    int count = right;
    while (left < right) {
      int mid = (left + right) >> 1;
      if (isEmpty(root, depth, mid)) {
        right = mid;
      } else if (mid != left) {
        left = mid;
      } else {
        if (isEmpty(root, depth, right)) {
          break;
        } else {
          left = right;
        }
      }
    }
    return count + left + 1;
  }

  private static boolean isEmpty(TreeNode root, int depth, int path) {
    TreeNode node = root;
    String binaryString = Integer.toBinaryString(path);
    int delta = depth - binaryString.length();
    while (delta > 0) {
      node = node.left;
      delta--;
    }
    for (char c : binaryString.toCharArray()) {
      node = c == '0' ? node.left : node.right;
    }
    return node == null;
  }
}
