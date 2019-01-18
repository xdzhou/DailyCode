package com.loic.leetcode.hard;

/**
 * 42. Trapping Rain Water
 * https://leetcode.com/problems/trapping-rain-water/
 * <p>
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 */
public final class TrappingRainWater {

  public static int trap(int... heights) {
    if (heights.length < 3) {
      return 0;
    }
    //current highest from current index to right
    int[] rightHighest = new int[heights.length];

    rightHighest[heights.length - 1] = heights[heights.length - 1];
    for (int i = heights.length - 2; i >= 0; i--) {
      rightHighest[i] = Math.max(heights[i], rightHighest[i + 1]);
    }
    int sum = 0;
    //current highest from left to current index
    int heighest = heights[0];
    for (int i = 1; i < heights.length - 1; i++) {
      if (heights[i] >= heighest) {
        heighest = heights[i];
      } else {
        sum += (Math.min(heighest, rightHighest[i]) - heights[i]);
      }
    }
    return sum;
  }
}
