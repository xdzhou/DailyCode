package com.loic.leetcode.medium;

/*
 * 11. Container With Most Water
 * https://leetcode.com/problems/container-with-most-water/
 *
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines,
 * which together with x-axis forms a container, such that the container contains the most water.
 *
 * Note: You may not slant the container and n is at least 2.
 */
public final class ContainerMostWater {

  /*
   * https://leetcode.com/problems/container-with-most-water/discuss/6099/yet-another-way-to-see-what-happens-in-the-on-algorithm
   */
  public static int optimal(int[] input) {
    int head = 0;
    int tail = input.length - 1;
    int max = 0;
    while (head < tail) {
      max = Math.max(max, (tail - head) * Math.min(input[head], input[tail]));
      if (input[head] < input[tail]) {
        //we remove the lower column, because all the container with this lower column will has less water
        head++;
      } else {
        tail--;
      }
    }
    return max;
  }

  public static int bruteForce(int[] input) {
    if (input.length < 2) {
      return 0;
    }
    int max = Math.min(input[0], input[1]);
    for (int i = 2; i < input.length; i++) {
      for (int j = 0; j < i; j++) {
        max = Math.max(max, (i - j) * Math.min(input[i], input[j]));
      }
    }
    return max;
  }
}
