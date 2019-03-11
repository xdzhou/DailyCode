package com.loic.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

import com.loic.leetcode.helper.TreeNode;

/**
 * 95. Unique Binary Search Trees II
 * https://leetcode.com/problems/unique-binary-search-trees-ii/
 *
 * Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.
 *
 * Example:
 *
 * Input: 3
 * Output:
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * Explanation:
 * The above output corresponds to the 5 unique BST's shown below:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 */
public final class UniqueBST2 {

  public static List<TreeNode> generateTrees(int n) {
    List<TreeNode> result = new ArrayList<>();
    if (n > 0) {
      process(result, 1, n);
    }
    return result;
  }

  /**
   * generate BST for values [from:to]
   */
  private static void process(List<TreeNode> result, int from, int to) {
    if (from > to) {
      result.add(null);
    } else if (from == to) {
      result.add(new TreeNode(from));
    } else {
      for (int root = from; root <= to; root++) {
        int initSize = result.size();
        // generate left sub tree
        process(result, from, root - 1);
        int leftSubNodeCount = result.size() - initSize;
        List<TreeNode> leftSubNods = new ArrayList<>(leftSubNodeCount);
        for (int i = 0; i < leftSubNodeCount; i++) {
          leftSubNods.add(result.remove(result.size() - 1));
        }
        // generate right sub tree
        process(result, root + 1, to);
        int rightSubNodeCount = result.size() - initSize;
        // construct nodes
        for (int i = 0; i < leftSubNods.size(); i++) {
          // replace the node in result list only for last loop
          boolean needAdd = (i != leftSubNodeCount - 1);
          for (int j = 0; j < rightSubNodeCount; j++) {
            int rightSubIndex = initSize + j;
            TreeNode node = new TreeNode(root);
            node.left = leftSubNods.get(i);
            node.right = result.get(rightSubIndex);
            if (needAdd) {
              result.add(node);
            } else {
              result.set(rightSubIndex, node);
            }
          }
        }
      }
    }
  }
}
