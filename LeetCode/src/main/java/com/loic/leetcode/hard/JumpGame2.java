package com.loic.leetcode.hard;

import java.util.Arrays;

public final class JumpGame2 {

  public static int optimal(int... nums) {
    int jumpCount = 0;
    // if we jump 'jumpCount' times, we could go to index range [from:to]
    int from = 0, to = 0;
    // the farthest index we can jump to, from index range [from:to]
    // and index range [to+1:farthest] will be jumping 'jumpCount+1' times range
    int farthest = 0;
    // ATTENTION: here use 'i < nums.length-1', as we increase the jumpCount when reaching the end of range
    // specially if the 'nums.length-1' is the end of a range, it will return a bad value
    for (int i = 0; i < nums.length - 1; i++) {
      farthest = Math.max(farthest, i + nums[i]);
      if (i == to) {
        jumpCount++;
        from = i + 1;
        to = farthest;
      }
    }
    return jumpCount;
  }

  public static int dpResolve(int... nums) {
    int[] dp = new int[nums.length];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;
    for (int i = 0; i < nums.length - 1; i++) {
      // the dpResolve count if we dpResolve from index i
      int jumpCount = dp[i] + 1;
      for (int jump = Math.min(nums[i], nums.length - 1 - i); jump >= 1; jump--) {
        int jumpIndex = i + jump;
        if (dp[jumpIndex] > jumpCount) {
          dp[jumpIndex] = jumpCount;
        }
      }
    }
    return dp[dp.length - 1];
  }
}
