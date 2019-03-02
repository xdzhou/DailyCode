package com.loic.leetcode.hard;

import java.util.PriorityQueue;

/**
 * 84. Largest Rectangle in Histogram
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 * <p>
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
 * find the area of largest rectangle in the histogram.
 * <p>
 * Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
 * <p>
 * The largest rectangle is shown in the shaded area, which has area = 10 unit.
 * <p>
 * Example:
 * <p>
 * Input: [2,1,5,6,2,3]
 * Output: 10
 */
public final class LargestRectangleInHistogram {

  /**
   * get left and right boundary of every column
   */
  public static int boundaryArea(int... heights) {
    if (heights.length == 0) {
      return 0;
    } else if (heights.length == 1) {
      return heights[0];
    }
    int[] leftBoundary = new int[heights.length];
    int[] rightBoundary = new int[heights.length];
    // compute left boundary
    leftBoundary[0] = -1;
    for (int i = 1; i < heights.length; i++) {
      int left = i - 1;
      while (left >= 0 && heights[left] >= heights[i]) {
        left = leftBoundary[left];
      }
      leftBoundary[i] = left;
    }
    // compute right boundary
    rightBoundary[heights.length - 1] = heights.length;
    for (int i = heights.length - 2; i >= 0; i--) {
      int right = i + 1;
      while (right < heights.length && heights[right] >= heights[i]) {
        right = rightBoundary[right];
      }
      rightBoundary[i] = right;
    }
    int max = 0;
    for (int i = 0; i < heights.length; i++) {
      max = Math.max(max, heights[i] * (rightBoundary[i] - leftBoundary[i] - 1));
    }
    return max;
  }

  public static int divideConquerArea(int... heights) {
    return process(0, heights.length - 1, heights);
  }

  /**
   * Divide Conquer solution:
   * idea is to to split the array into two parts, divide by min height
   */
  private static int process(int from, int to, int... heights) {
    if (from > to) {
      return 0;
    } else if (from == to) {
      return heights[from];
    }
    int minIndex = from;
    for (int i = from + 1; i <= to; i++) {
      if (heights[i] < heights[minIndex]) {
        minIndex = i;
      }
    }
    int max = (to + 1 - from) * heights[minIndex];
    int leftMax = process(from, minIndex - 1, heights);
    int rightMax = process(minIndex + 1, to, heights);
    return Math.max(max, Math.max(leftMax, rightMax));
  }

  public static int area(int... heights) {
    // currently the merged rectangle width
    int[] rectangleWidth = new int[heights.length];

    PriorityQueue<Column> pq = new PriorityQueue<>((col1, col2) -> {
      // we want sort from big to small
      int result = Integer.compare(col2.value, col1.value);
      return result == 0 ? Integer.compare(col2.index, col1.index) : result;
    });
    for (int i = 0; i < heights.length; i++) {
      pq.add(new Column(i, heights[i]));
    }
    int max = 0;

    while (!pq.isEmpty()) {
      Column pair = pq.poll();
      int leftMergeNum = pair.index - 1 >= 0 ? rectangleWidth[pair.index - 1] : 0;
      int rightMergeNum = pair.index + 1 < heights.length ? rectangleWidth[pair.index + 1] : 0;
      // we just need update the mergeNum of the boundary (leftmost & rightmost)
      int mergeWidth = leftMergeNum + rightMergeNum + 1;
      rectangleWidth[pair.index - leftMergeNum] = mergeWidth;
      rectangleWidth[pair.index + rightMergeNum] = mergeWidth;
      max = Math.max(max, mergeWidth * pair.value);
    }
    return max;
  }

  private static class Column {
    private final int index;
    private final int value;

    private Column(int index, int value) {
      this.index = index;
      this.value = value;
    }
  }
}
