package com.loic.leetcode.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import com.loic.leetcode.helper.TreeNode;

/**
 * 94. Binary Tree Inorder Traversal
 * https://leetcode.com/problems/binary-tree-inorder-traversal/
 * <p>
 * Given a binary tree, return the inorder traversal of its nodes' values.
 * <p>
 * Example:
 * <p>
 * Input: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * Output: [1,3,2]
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 */
public class BinaryTreeInorderTraversal {

  public static List<Integer> visit(TreeNode root) {
    List<Integer> result = new ArrayList<>();

    Stack<TreeNode> stack = new Stack<>();
    TreeNode curNode = root;

    while (curNode != null || !stack.isEmpty()) {
      while (curNode != null) {
        stack.push(curNode);
        curNode = curNode.left;
      }
      // when a node is pop, it means its left have been treated
      TreeNode centerNode = stack.pop();
      result.add(centerNode.val);
      // need treat its right
      curNode = centerNode.right;
    }

    return result;
  }

  public static List<Integer> visit2(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    Stack<TreeNode> stack = new Stack<>();
    Set<TreeNode> rePushed = new HashSet<>();
    if (root != null) {
      stack.push(root);
    }
    while (!stack.isEmpty()) {
      TreeNode node = stack.pop();
      if (rePushed.remove(node)) {
        result.add(node.val);
      } else {
        if (node.right != null) {
          stack.push(node.right);
        }
        stack.push(node);
        rePushed.add(node);
        if (node.left != null) {
          stack.push(node.left);
        }
      }
    }
    return result;
  }
}
