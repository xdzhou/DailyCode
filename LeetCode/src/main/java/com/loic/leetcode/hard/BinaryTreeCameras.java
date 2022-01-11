package com.loic.leetcode.hard;

import com.loic.leetcode.annotation.RelatedTopic;
import com.loic.leetcode.annotation.Topic;
import com.loic.leetcode.helper.TreeNode;

/**
 * 968. Binary Tree Cameras
 * <p>
 * You are given the root of a binary tree. We install cameras on the tree nodes where
 * each camera at a node can monitor its parent, itself, and its immediate children.
 * <p>
 * Return the minimum number of cameras needed to monitor all nodes of the tree.
 * <p>
 * Example 1:
 * Input: root = [0,0,null,0,0]
 * Output: 1
 * Explanation: One camera is enough to monitor all nodes if placed as shown.
 * <p>
 * Example 2:
 * Input: root = [0,0,null,0,null,0,null,null,0]
 * Output: 2
 * Explanation: At least two cameras are needed to monitor all nodes of the tree.
 * The above image shows one of the valid configurations of camera placement.
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [1, 1000].
 * Node.val == 0
 */
public class BinaryTreeCameras {

  @RelatedTopic(topics = Topic.Cache)
  public static int minCameraCover(TreeNode root) {
    if (root == null) {
      return 0;
    }
    if (root.val > 0) {
      return root.val;
    }
    // put camera on root itself
    int result1 = 1 + coverGrandChildren(root);
    // put camera on left child
    int result2 = root.left == null ? Integer.MAX_VALUE : 1 + minCameraCover(root.right) + coverGrandChildren(root.left);
    // put camera on right child
    int result3 = root.right == null ? Integer.MAX_VALUE : 1 + minCameraCover(root.left) + coverGrandChildren(root.right);

    int result = Math.min(result1, Math.min(result2, result3));
    root.val = result;
    return result;
  }

  private static int coverGrandChildren(TreeNode root) {
    int result = 0;
    if (root.left != null) {
      result += Math.min(minCameraCover(root.left), minCameraCover(root.left.left) + minCameraCover(root.left.right));
    }
    if (root.right != null) {
      result += Math.min(minCameraCover(root.right), minCameraCover(root.right.left) + minCameraCover(root.right.right));
    }
    return result;
  }
}
