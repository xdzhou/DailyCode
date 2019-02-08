package com.loic.leetcode.medium;

/**
 * 55. Jump Game
 * https://leetcode.com/problems/jump-game/
 * <p>
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * <p>
 * Each element in the array represents your maximum jump length at that position.
 * <p>
 * Determine if you are able to reach the last index.
 */
public final class JumpGame {

  public static boolean canJump(int... nums) {
    int farthest = -1;
    int boundary = 0;
    for (int i = 0; i < nums.length - 1; i++) {
      // from index i, the current farthest index could be jump to
      int curFarthest = i + nums[i];
      if (curFarthest > farthest) {
        farthest = curFarthest;
      }
      if (boundary == i) {
        // reach current boundary, update next boundary
        if (boundary == farthest) {
          return false;
        }
        boundary = farthest;
      }
    }
    return true;
  }
}
