package com.loic.leetcode.medium;

/**
 * 287. Find the Duplicate Number
 * https://leetcode.com/problems/find-the-duplicate-number/
 * <p>
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,3,4,2,2]
 * Output: 2
 * Example 2:
 * <p>
 * Input: [3,1,3,4,2]
 * Output: 3
 * Note:
 * <p>
 * You must not modify the array (assume the array is read only).
 * You must use only constant, O(1) extra space.
 * Your runtime complexity should be less than O(n2).
 * There is only one duplicate number in the array, but it could be repeated more than once.
 */
public class FindDuplicateNumber {

  /**
   * image a linked list like:
   * num[0]->num[num[0]]->num[num[num[0]]]->....
   * <p>
   * and find the cycle entrance point
   */
  public static int findDuplicate(int... nums) {
    if (nums.length < 3) {
      return nums[0];
    }
    int slow = nums[nums[0]]; // 1 step
    int quick = nums[slow]; // 2 steps
    while (slow != quick) {
      slow = nums[slow];
      quick = nums[nums[quick]];
    }
    slow = nums[0];
    while (slow != quick) {
      slow = nums[slow];
      quick = nums[quick];
    }
    return slow;
  }
}
