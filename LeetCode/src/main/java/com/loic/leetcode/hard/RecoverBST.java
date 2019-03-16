package com.loic.leetcode.hard;

import java.util.Stack;

import com.loic.leetcode.helper.TreeNode;

/**
 * 99. Recover Binary Search Tree
 * https://leetcode.com/problems/recover-binary-search-tree/
 *
 * Two elements of a binary search tree (BST) are swapped by mistake.
 *
 * Recover the tree without changing its structure.
 *
 * Example 1:
 *
 * Input: [1,3,null,null,2]
 *
 *    1
 *   /
 *  3
 *   \
 *    2
 *
 * Output: [3,1,null,null,2]
 *
 *    3
 *   /
 *  1
 *   \
 *    2
 */
public class RecoverBST {

  public static void recoverTree(TreeNode root) {
    InOrderVisitor visitor = new InOrderVisitor();

    Stack<TreeNode> stack = new Stack<>();
    TreeNode curNode = root;
    boolean swaped = false;
    while (curNode != null || !stack.isEmpty()) {
      while (curNode != null) {
        stack.push(curNode);
        curNode = curNode.left;
      }
      TreeNode next = stack.pop();
      if (!visitor.next(next)) {
        swaped = true;
        break;
      }
      curNode = next.right;
    }
    if (!swaped) {
      // can't find the second node to swap,
      // that means the second is the one just next fist node
      swap(visitor.fistSwapNode, visitor.fistSwapNextNode);
    }
  }

  private static void swap(TreeNode node1, TreeNode node2) {
    int tmp = node1.val;
    node1.val = node2.val;
    node2.val = tmp;
  }

  private static class InOrderVisitor {

    private TreeNode preNode;
    // the first node to be swap
    private TreeNode fistSwapNode;
    // the node next first node to be swap
    private TreeNode fistSwapNextNode;

    /**
     * visit the BST by in order traversal
     *
     * @return true if continue to visit
     */
    private boolean next(TreeNode node) {
      if (preNode != null && node.val < preNode.val) {
        // find violation, next node value is less than previous node
        if (fistSwapNode == null) {
          fistSwapNode = preNode;
          fistSwapNextNode = node;
        } else {
          swap(fistSwapNode, node);
          return false;
        }
      }
      preNode = node;
      return true;
    }
  }
}
