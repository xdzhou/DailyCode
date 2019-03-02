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
