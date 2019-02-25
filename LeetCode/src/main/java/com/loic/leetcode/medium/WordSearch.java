package com.loic.leetcode.medium;

/**
 * 79. Word Search
 * https://leetcode.com/problems/word-search/
 * <p>
 * Given a 2D board and a word, find if the word exists in the grid.
 * <p>
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
 * <p>
 * Example:
 * <p>
 * board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * <p>
 * Given word = "ABCCED", return true.
 * Given word = "SEE", return true.
 * Given word = "ABCB", return false.
 */
public final class WordSearch {

  public static boolean exist(char[][] board, String word) {
    if (word == null || word.length() == 0 || board.length == 0 || board[0].length == 0) {
      return false;
    }
    for (int row = 0; row < board.length; row++) {
      for (int col = 0; col < board[0].length; col++) {
        if (exist(board, word, row, col, 0)) {
          return true;
        }
      }
    }
    return false;
  }

  private static boolean exist(char[][] board, String word, int row, int col, int length) {
    if (word.length() == length) {
      return true;
    }
    if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
      return false;
    }
    if (word.charAt(length) != board[row][col]) {
      return false;
    }
    //mark this position been visited
    char preValue = board[row][col];
    board[row][col] = '#';
    boolean result = exist(board, word, row - 1, col, length + 1)
      || exist(board, word, row + 1, col, length + 1)
      || exist(board, word, row, col - 1, length + 1)
      || exist(board, word, row, col + 1, length + 1);
    board[row][col] = preValue;
    return result;
  }
}
