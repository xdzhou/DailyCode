package com.loic.leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 51. N-Queens
 * https://leetcode.com/problems/n-queens/
 * <p>
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 */
public final class NQueens {

  /**
   * each two queen could not on the same line, col, & diagonal
   */
  public static List<List<String>> solve(int n) {
    char[][] board = new char[n][n];
    for (char[] chars : board) {
      Arrays.fill(chars, '.');
    }
    List<List<String>> result = new ArrayList<>();
    process(board, result, 0, new HashSet<>(), new HashSet<>(), new HashSet<>());
    return result;
  }

  private static void process(char[][] board, List<List<String>> result, int line, Set<Integer> colFilled, Set<Integer> diagonal1Filled, Set<Integer> diagonal2Filled) {
    if (line == board.length) {
      List<String> temp = new ArrayList<>();
      for (char[] chars : board) {
        temp.add(new String(chars));
      }
      result.add(temp);
    } else {
      for (int col = 0; col < board.length; col++) {
        // diagonal from top left to bottom right
        int diagonal1 = col - line;
        // diagonal from top right to bottom left
        int diagonal2 = board.length-1-line-col;
        if (!colFilled.contains(col) && !diagonal1Filled.contains(diagonal1) && !diagonal2Filled.contains(diagonal2)) {
          board[line][col] = 'Q';
          colFilled.add(col);
          diagonal1Filled.add(diagonal1);
          diagonal2Filled.add(diagonal2);
          process(board, result, line + 1, colFilled, diagonal1Filled, diagonal2Filled);
          diagonal2Filled.remove(diagonal2);
          diagonal1Filled.remove(diagonal1);
          colFilled.remove(col);
          board[line][col] = '.';
        }
      }
    }
  }
}
