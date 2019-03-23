package com.loic.leetcode.easy;

import com.loic.leetcode.helper.TreeNode;

/**
 * 108. Convert Sorted Array to Binary Search Tree
 * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 *
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 *
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 *
 * Example:
 *
 * Given the sorted array: [-10,-3,0,5,9],
 *
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 */
public class SortedArray2BST {

  public static TreeNode toBST(int... nums) {
    return toBST(0, nums.length - 1, nums);
  }

  private static TreeNode toBST(int from, int to, int... nums) {
    if (from > to) {
      return null;
    } else if (from == to) {
      return new TreeNode(nums[from]);
    }
    int sum = from + to;
    int mid = sum % 2 == 0 ? sum >>> 1 : (sum + 1) >>> 1;
    TreeNode root = new TreeNode(nums[mid]);
    root.left = toBST(from, mid - 1, nums);
    root.right = toBST(mid + 1, to, nums);
    return root;
  }
}
