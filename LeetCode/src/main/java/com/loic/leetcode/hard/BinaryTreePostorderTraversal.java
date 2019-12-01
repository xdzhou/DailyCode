package com.loic.leetcode.hard;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import com.loic.leetcode.helper.TreeNode;

/**
 * 145. Binary Tree Postorder Traversal
 * <p>
 * Given a binary tree, return the postorder traversal of its nodes' values.
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
 * Output: [3,2,1]
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 */
public class BinaryTreePostorderTraversal {

  public static List<Integer> postorderTraversal(TreeNode root) {
    Stack<TreeNode> stack = new Stack<>();
    if (root != null) {
      stack.push(root);
    }
    List<Integer> result = new LinkedList<>();
    Set<TreeNode> rePushed = new HashSet<>();
    while (!stack.isEmpty()) {
      TreeNode node = stack.pop();
      if (rePushed.remove(node)) {
        result.add(node.val);
      } else {
        rePushed.add(node);
        stack.push(node);
        if (node.right != null) {
          stack.push(node.right);
        }
        if (node.left != null) {
          stack.push(node.left);
        }
      }
    }
    return result;
  }
}
