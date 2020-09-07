package com.loic.leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * 312. Burst Balloons
 * https://leetcode.com/problems/burst-balloons/
 * <p>
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
 * <p>
 * Find the maximum coins you can collect by bursting the balloons wisely.
 * <p>
 * Note:
 * <p>
 * You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * Example:
 * <p>
 * Input: [3,1,5,8]
 * Output: 167
 * Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 * coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 */
public class BurstBalloons {
  /**
   * Which is quite often seen in dp problem analysis. That is reverse thinking.
   * Like I said the coins you get for a balloon does not depend on the balloons already burst. Therefore
   * instead of divide the problem by the first balloon to burst, we divide the problem by the last balloon to burst.
   * <p>
   * Why is that? Because only the first and last balloons we are sure of their adjacent balloons before hand!
   * <p>
   * For the first we have nums[i-1]*nums[i]*nums[i+1] for the last we have nums[-1]*nums[i]*nums[n].
   * <p>
   * OK. Think about n balloons if i is the last one to burst, what now?
   * <p>
   * We can see that the balloons is again separated into 2 sections. But this time since the balloon i is the last balloon of all to burst,
   * the left and right section now has well defined boundary and do not affect each other! Therefore we can do either recursive method with memoization or dp.
   */
  public static int maxCoins(int... nums) {
    List<Integer> numbers = new ArrayList<>();
    numbers.add(1);
    for (int num : nums) {
      // remove 0, as it has no effet
      if (num != 0) {
        numbers.add(num);
      }
    }
    numbers.add(1);
    // add 1 at beginning & end, make the problem to burst oll the ballons except the start & end
    int[][] memo = new int[numbers.size()][numbers.size()];
    return maxCoins(memo, 0, numbers.size() - 1, numbers);
  }

  private static int maxCoins(int[][] memo, int from, int to, List<Integer> numbers) {
    if (memo[from][to] > 0) {
      return memo[from][to];
    }
    int coins = Integer.MIN_VALUE;
    if (to - from <= 1) {
      coins = 0;
    } else {
      for (int i = from + 1; i <= to - 1; i++) {
        int val = maxCoins(memo, from, i, numbers) + maxCoins(memo, i, to, numbers) + numbers.get(from) * numbers.get(i) * numbers.get(to);
        coins = Math.max(coins, val);
      }
    }
    memo[from][to] = coins;
    return coins;
  }

}
