package com.loic.leetcode.hard;

import com.loic.leetcode.helper.TreeNode;

/**
 * 297. Serialize and Deserialize Binary Tree
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 * <p>
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 * <p>
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 * <p>
 * Example:
 * <p>
 * You may serialize the following tree:
 * <p>
 * 1
 * / \
 * 2   3
 * / \
 * 4   5
 * <p>
 * as "[1,2,3,null,null,4,5]"
 * Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
 * <p>
 * Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 */
public class Codec {
  private final static char LEFT = 'L';
  private final static char RIGHT = 'R';
  private final static char NULL = 'N';

  public String serialize(TreeNode root) {
    if (root == null) {
      return String.valueOf(NULL);
    }
    if (root.left == null && root.right == null) {
      return String.valueOf(root.val);
    }
    return String.valueOf(root.val) +
        LEFT +
        serialize(root.left) +
        RIGHT +
        serialize(root.right);
  }

  public TreeNode deserialize(String data) {
    TreeParser parser = new TreeParser(data);
    return parser.readTree();
  }

  private static final class TreeParser {
    private final String data;
    private int curPosition = 0;

    private TreeParser(String data) {
      this.data = data;
    }

    TreeNode readTree() {
      if (data.charAt(curPosition) == NULL) {
        curPosition++;
        return null;
      }
      TreeNode node = new TreeNode(readNumber());
      if (curPosition < data.length() && data.charAt(curPosition) == LEFT) {
        curPosition++; //skip 'L'
        node.left = readTree();
        curPosition++; //skip 'R'
        node.right = readTree();
      }
      return node;
    }

    int readNumber() {
      int num = 0;
      boolean positive = true;
      if (data.charAt(curPosition) == '-') {
        curPosition++;
        positive = false;
      }
      char c = data.charAt(curPosition);
      while (c != LEFT && c != RIGHT) {
        num = num * 10 + (c - '0');
        curPosition++;
        c = curPosition >= data.length() ? LEFT : data.charAt(curPosition);
      }
      return positive ? num : -num;
    }
  }
}
