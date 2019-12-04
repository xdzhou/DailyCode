package com.loic.leetcode.medium;

/**
 * 152. Maximum Product Subarray
 * <p>
 * Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
 * <p>
 * Example 1:
 * <p>
 * Input: [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * Example 2:
 * <p>
 * Input: [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 */
public class MaxProductSubarray {

  public static int maxProduct(int... nums) {
    int max = Integer.MIN_VALUE, product = 1, len = nums.length;
    for (int i = 0; i < len; i++) {
      max = Math.max(product *= nums[i], max);
      if (nums[i] == 0) {
        product = 1;
      }
    }
    product = 1;
    for (int i = len - 1; i >= 0; i--) {
      max = Math.max(product *= nums[i], max);
      if (nums[i] == 0) {
        product = 1;
      }
    }
    return max;
  }

  public static int maxProduct2(int... nums) {
    return Math.max(maxProduct(nums, true), maxProduct(nums, false));
  }

  private static int maxProduct(int[] nums, boolean fromLeft) {
    int maxProduct = Integer.MIN_VALUE;
    int product = 1;
    int preNeg = 0;
    for (int i = 0; i < nums.length; i++) {
      int num = fromLeft ? nums[i] : nums[nums.length - 1 - i];
      maxProduct = Math.max(maxProduct, num);
      if (num == 0) {
        product = 1;
        preNeg = 0;
      } else if (num < 0) {
        if (preNeg == 0) {
          preNeg = product * num;
          product = 1;
        } else {
          product = product * preNeg * num;
          preNeg = 0;
          maxProduct = Math.max(maxProduct, product);
        }
      } else {
        product *= num;
        maxProduct = Math.max(maxProduct, product);
      }
    }
    return maxProduct;
  }
}
