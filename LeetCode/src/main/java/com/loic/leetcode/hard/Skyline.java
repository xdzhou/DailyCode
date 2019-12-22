package com.loic.leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 218. The Skyline Problem
 * <p>
 * A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).
 * <p>
 * Buildings Skyline Contour
 * The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
 * <p>
 * For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .
 * <p>
 * The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.
 * <p>
 * For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
 * <p>
 * Notes:
 * <p>
 * The number of buildings in any input list is guaranteed to be in the range [0, 10000].
 * The input list is already sorted in ascending order by the left x position Li.
 * The output list must be sorted by the x position.
 * There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]
 */
public class Skyline {

  public static List<List<Integer>> getSkyline(int[][] buildings) {
    if (buildings.length == 0) {
      return Collections.emptyList();
    }
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < buildings.length; i++) {
      int[] building = buildings[i];
      min = Math.min(min, building[0]);
      max = Math.max(max, building[1]);
    }
    SegTreeNode root = new SegTreeNode(min, max, 0);
    for (int i = 0; i < buildings.length; i++) {
      int[] building = buildings[i];
      root.addSegment(building[0], building[1], building[2]);
    }
    //in order traverse
    List<List<Integer>> result = new ArrayList<>();

    Stack<SegTreeNode> stack = new Stack<>();
    int preHeight = -1;
    SegTreeNode node = root;
    while (!stack.isEmpty() || node != null) {
      while (node != null) {
        stack.push(node);
        node = node.left;
      }
      SegTreeNode popNode = stack.pop();
      if (popNode.left == null && popNode.maxHeight != preHeight) {
        preHeight = popNode.maxHeight;
        if (!result.isEmpty() || preHeight > 0) {
          result.add(Arrays.asList(popNode.start, preHeight));
        }
      }
      node = popNode.right;
    }
    result.add(Arrays.asList(max, 0));
    return result;
  }

  private static final class SegTreeNode {
    private final int start, end;
    private int maxHeight;
    //left is [start, mid], right is (mid,end]
    private SegTreeNode left, right;

    private SegTreeNode(int start, int end, int maxHeight) {
      this.start = start;
      this.end = end;
      this.maxHeight = maxHeight;
    }

    public void addSegment(int from, int to, int height) {
      //System.out.println(start + " , " + end + ", add segment : [" + from + " , " + to + "] " + height);
      int mid = (start >> 1) + (end >> 1);
      if (start % 2 == 1 && end % 2 == 1) {
        mid++;
      }
      if (left == null) { // leaf node
        if (height <= maxHeight) {
          return;
        } else if (start == from && end == to) {
          maxHeight = height;
          return;
        } else {
          left = new SegTreeNode(start, mid, maxHeight);
          right = new SegTreeNode(mid, end, maxHeight);
        }
      }
      maxHeight = Math.max(maxHeight, height);
      if (to <= mid) {
        left.addSegment(from, to, height);
      } else if (mid <= from) {
        right.addSegment(from, to, height);
      } else {
        left.addSegment(from, mid, height);
        right.addSegment(mid, to, height);
      }
    }
  }
}
