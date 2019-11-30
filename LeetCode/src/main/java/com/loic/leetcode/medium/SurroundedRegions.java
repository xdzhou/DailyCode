package com.loic.leetcode.medium;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * 130. Surrounded Regions
 * <p>
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
 * <p>
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 * <p>
 * Example:
 * <p>
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * After running your function, the board should be:
 * <p>
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * Explanation:
 * <p>
 * Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped to 'X'.
 * Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'.
 * Two cells are connected if they are adjacent cells connected horizontally or vertically.
 */
public class SurroundedRegions {

  public static void solve(char[][] board) {
    if (board.length == 0 || board[0].length == 0) {
      return;
    }
    Set<Integer> connected = new HashSet<>();
    // first row
    IntStream.range(0, board[0].length)
      .filter(col -> board[0][col] == 'O')
      .forEach(col -> fill(board, 0, col, connected));
    // last row
    IntStream.range(0, board[0].length)
      .filter(col -> board[board.length - 1][col] == 'O')
      .forEach(col -> fill(board, board.length - 1, col, connected));
    // first col
    IntStream.range(0, board.length)
      .filter(row -> board[row][0] == 'O')
      .forEach(row -> fill(board, row, 0, connected));
    // last col
    IntStream.range(0, board.length)
      .filter(row -> board[row][board[0].length - 1] == 'O')
      .forEach(row -> fill(board, row, board[0].length - 1, connected));

    for (int row = 0; row < board.length; row++) {
      for (int col = 0; col < board[0].length; col++) {
        if (board[row][col] == 'O' && !connected.contains(row * (board[0].length) + col)) {
          board[row][col] = 'X';
        }
      }
    }
  }

  private static void fill(char[][] board, int row, int col, Set<Integer> connected) {
    if (connected.add(row * (board[0].length) + col)) {
      // UP
      if (row > 0 && board[row - 1][col] == 'O') {
        fill(board, row - 1, col, connected);
      }
      // DOWN
      if (row + 1 < board.length && board[row + 1][col] == 'O') {
        fill(board, row + 1, col, connected);
      }
      // LEFT
      if (col > 0 && board[row][col - 1] == 'O') {
        fill(board, row, col - 1, connected);
      }
      // RIGHT
      if (col + 1 < board[0].length && board[row][col + 1] == 'O') {
        fill(board, row, col + 1, connected);
      }
    }
  }
}
