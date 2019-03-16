package com.loic.leetcode.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class TreeNode {
  public int val;
  public TreeNode left;
  public TreeNode right;

  public TreeNode(int x) {
    val = x;
  }

  /**
   * create a Binary tree from level order
   */
  public static TreeNode fromLevelOrder(List<Integer> list) {
    if (list.isEmpty()) {
      return null;
    }
    TreeNode root = new TreeNode(list.get(0));
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    int index = 1;
    while (!queue.isEmpty()) {
      int nodeSize = queue.size();
      for (int i = 0; i < nodeSize; i++) {
        TreeNode node = queue.poll();
        TreeNode left = generate(list, index++);
        TreeNode right = generate(list, index++);
        node.left = left;
        node.right = right;
        if (left != null) {
          queue.add(left);
        }
        if (right != null) {
          queue.add(right);
        }
      }
    }
    return root;
  }

  public static TreeNode fromLevelOrder(int... values) {
    return fromLevelOrder(Arrays.stream(values).boxed().collect(Collectors.toList()));
  }

  private static TreeNode generate(List<Integer> list, int index) {
    return (index >= list.size() || list.get(index) == null) ?
      null : new TreeNode(list.get(index));
  }

  @Override
  public String toString() {
    List<Integer> levelOrder = new ArrayList<>();
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(this);
    while (!queue.isEmpty()) {
      TreeNode node = queue.poll();
      levelOrder.add(node == null ? null : node.val);
      if (node != null) {
        queue.add(node.left);
        queue.add(node.right);
      }
    }
    while (levelOrder.size() > 0 && levelOrder.get(levelOrder.size() - 1) == null) {
      levelOrder.remove(levelOrder.size() - 1);
    }
    return levelOrder.toString();
  }
}
