package com.loic.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 118. Pascal's Triangle
 * https://leetcode.com/problems/pascals-triangle/
 *
 * Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.
 *
 * In Pascal's triangle, each number is the sum of the two numbers directly above it.
 *
 * Example:
 *
 * Input: 5
 * Output:
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 */
public class PascalTriangle {

  public static List<List<Integer>> generate(int numRows) {
    List<List<Integer>> result = new ArrayList<>(numRows);
    for (int i = 0; i < numRows; i++) {
      List<Integer> row = new ArrayList<>(i + 1);
      if (i == 0) {
        row.add(1);
      } else {
        for (int index = 0; index < i + 1; index++) {
          int leftTop = index - 1 < 0 ? 0 : result.get(i - 1).get(index - 1);
          int rightTop = index == i ? 0 : result.get(i - 1).get(index);
          row.add(leftTop + rightTop);
        }
      }
      result.add(row);
    }
    return result;
  }
}
