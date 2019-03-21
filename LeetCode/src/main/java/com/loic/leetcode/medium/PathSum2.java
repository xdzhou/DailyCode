package com.loic.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

import com.loic.leetcode.helper.TreeNode;

/**
 * 113. Path Sum II
 * https://leetcode.com/problems/path-sum-ii/
 *
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 *
 * Note: A leaf is a node with no children.
 *
 * Example:
 *
 * Given the below binary tree and sum = 22,
 *
 *       5
 *      / \
 *     4   8
 *    /   / \
 *   11  13  4
 *  /  \    / \
 * 7    2  5   1
 * Return:
 *
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 */
public class PathSum2 {

  public static List<List<Integer>> path(TreeNode root, int sum) {
    List<List<Integer>> result = new ArrayList<>();
    process(root, sum, result, new ArrayList<>());
    return result;
  }

  private static void process(TreeNode root, int sum, List<List<Integer>> result, List<Integer> path) {
    if (root != null) {
      path.add(root.val);
      if (root.left == null && root.right == null) {
        if (root.val == sum) {
          result.add(new ArrayList<>(path));
        }
      } else {
        process(root.left, sum - root.val, result, path);
        process(root.right, sum - root.val, result, path);
      }
      path.remove(path.size() - 1);
    }
  }
}
