package com.loic.leetcode.hard;

import java.util.HashSet;
import java.util.Set;

/**
 * 52. N-Queens II
 * https://leetcode.com/problems/n-queens-ii/
 * <p>
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 * Given an integer n, return the number of distinct solutions to the n-queens puzzle.
 */
public final class NQueens2 {
  public static int count(int n) {
    return process(n, 0, new HashSet<>(), new HashSet<>(), new HashSet<>());
  }

  private static int process(int n, int line, Set<Integer> colFilled, Set<Integer> diagonal1Filled, Set<Integer> diagonal2Filled) {
    int count = 0;
    if (line == n) {
      count++;
    } else {
      for (int col = 0; col < n; col++) {
        // diagonal from top left to bottom right
        int diagonal1 = col - line;
        // diagonal from top right to bottom left
        int diagonal2 = n - 1 - line - col;
        if (!colFilled.contains(col) && !diagonal1Filled.contains(diagonal1) && !diagonal2Filled
          .contains(diagonal2)) {
          colFilled.add(col);
          diagonal1Filled.add(diagonal1);
          diagonal2Filled.add(diagonal2);
          count += process(n, line + 1, colFilled, diagonal1Filled, diagonal2Filled);
          diagonal2Filled.remove(diagonal2);
          diagonal1Filled.remove(diagonal1);
          colFilled.remove(col);
        }
      }
    }
    return count;
  }
}
