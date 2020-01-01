package com.loic.leetcode.medium;

/**
 * 238. Product of Array Except Self
 * <p>
 * Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
 * <p>
 * Example:
 * <p>
 * Input:  [1,2,3,4]
 * Output: [24,12,8,6]
 * Note: Please solve it without division and in O(n).
 * <p>
 * Follow up:
 * Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
 */
public class ProductOfArrayExceptSelf {

  public static int[] compute(int... nums) {
    int[] output = new int[nums.length];
    for (int i = 0; i < nums.length; i++) {
      if (i == 0) {
        output[i] = 1;
      } else {
        output[i] = output[i - 1] * nums[i - 1];
      }
    }
    int rightProduct = nums[nums.length - 1];
    for (int i = nums.length - 2; i >= 0; i--) {
      output[i] *= rightProduct;
      rightProduct *= nums[i];
    }
    return output;
  }
}
