package com.loic.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.loic.leetcode.helper.TreeNode;

/**
 * 103. Binary Tree Zigzag Level Order Traversal
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 *
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its zigzag level order traversal as:
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 */
public class BinaryTreeZigzagLevelOrderTraversal {

  public static List<List<Integer>> zigzag(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root != null) {
      Queue<TreeNode> queue = new LinkedList<>();
      queue.add(root);
      // zig (from left) or zag (from right)
      boolean fromLeft = true;
      while (!queue.isEmpty()) {
        int size = queue.size();
        Integer[] data = new Integer[size];
        for (int i = 0; i < size; i++) {
          TreeNode node = queue.poll();
          data[fromLeft ? i : size - 1 - i] = node.val;
          if (node.left != null) {
            queue.add(node.left);
          }
          if (node.right != null) {
            queue.add(node.right);
          }
        }
        result.add(Arrays.asList(data));
        fromLeft = !fromLeft;
      }
    }
    return result;
  }
}
