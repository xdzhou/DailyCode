package com.loic.leetcode.medium;

import java.util.List;

/**
 * 120. Triangle
 * https://leetcode.com/problems/triangle/
 *
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
 *
 * For example, given the following triangle
 *
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 *
 * Note:
 *
 * Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
 */
public final class Triangle {

  public static int minimumTotal(List<List<Integer>> triangle) {
    if (triangle.isEmpty()) {
      return 0;
    }
    // dp is the min sum from top to this level
    int dp[][] = new int[2][triangle.size()];
    for (int level = 0; level < triangle.size(); level++) {
      int dpIndex = level % 2;
      int preDpIndex = (level - 1) % 2;
      if (level == 0) {
        dp[dpIndex][0] = triangle.get(0).get(0);
      } else {
        for (int i = 0; i <= level; i++) {
          int leftPathSum = (i - 1) < 0 ? Integer.MAX_VALUE : dp[preDpIndex][i - 1];
          int rightPathSum = (i == level) ? Integer.MAX_VALUE : dp[preDpIndex][i];
          // the min path sum depends on the left/right min path sum
          dp[dpIndex][i] = Integer.min(leftPathSum, rightPathSum) + triangle.get(level).get(i);
        }
      }
    }
    int min = Integer.MAX_VALUE;
    for (int val : dp[(triangle.size() - 1) % 2]) {
      min = Integer.min(min, val);
    }
    return min;
  }
}
