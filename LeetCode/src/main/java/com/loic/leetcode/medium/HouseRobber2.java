package com.loic.leetcode.medium;

import java.util.stream.IntStream;

/**
 * 213. House Robber II
 * <p>
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * <p>
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
 * <p>
 * Example 1:
 * <p>
 * Input: [2,3,2]
 * Output: 3
 * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
 * because they are adjacent houses.
 * Example 2:
 * <p>
 * Input: [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 */
public class HouseRobber2 {

  public static int rob(int... nums) {
    if (nums.length == 0) {
      return 0;
    } else if (nums.length <= 3) {
      return IntStream.of(nums).max().getAsInt();
    }
    int[] robFrom0 = rob(0, nums);
    int[] robFrom1 = rob(1, nums);
    return Math.max(robFrom0[0], robFrom1[1]);
  }

  private static int[] rob(int from, int... nums) {
    //exclude[i] is the max profit when robbing nums[from,i] but without robing house i
    //include[i] is the max profit when robbing nums[from,i] and with robing house i
    int exclude = 0;
    int include = nums[from];
    for (int i = from + 1; i < nums.length; i++) {
      int max = Math.max(include, exclude);
      include = nums[i] + exclude;
      exclude = max;
    }
    return new int[]{exclude, include};
  }
}
