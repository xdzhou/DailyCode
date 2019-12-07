package com.loic.leetcode.medium;

import java.util.Stack;

import com.loic.leetcode.helper.TreeNode;

/**
 * 173. Binary Search Tree Iterator
 * <p>
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 * <p>
 * Calling next() will return the next smallest number in the BST.
 * <p>
 * Example:
 * ****7
 * **3  15
 * ****9 20
 * <p>
 * BSTIterator iterator = new BSTIterator(root);
 * iterator.next();    // return 3
 * iterator.next();    // return 7
 * iterator.hasNext(); // return true
 * iterator.next();    // return 9
 * iterator.hasNext(); // return true
 * iterator.next();    // return 15
 * iterator.hasNext(); // return true
 * iterator.next();    // return 20
 * iterator.hasNext(); // return false
 */
public class BSTIterator {
  private final Stack<TreeNode> stack = new Stack<>();
  private TreeNode curNode;

  public BSTIterator(TreeNode root) {
    curNode = root;
  }

  /**
   * @return the next smallest number
   */
  public int next() {
    while (curNode != null) {
      stack.push(curNode);
      curNode = curNode.left;
    }
    // when a node is pop, it means its left have been treated
    TreeNode centerNode = stack.pop();
    // need treat its right
    curNode = centerNode.right;
    return centerNode.val;
  }

  /**
   * @return whether we have a next smallest number
   */
  public boolean hasNext() {
    return curNode != null || !stack.isEmpty();
  }
}
