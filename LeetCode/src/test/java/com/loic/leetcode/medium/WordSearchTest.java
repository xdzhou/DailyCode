package com.loic.leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WordSearchTest {

  @Test
  void exist() {
    char[][] board = new char[3][];
    board[0] = chars('A', 'B', 'C', 'E');
    board[1] = chars('S', 'F', 'C', 'S');
    board[2] = chars('A', 'D', 'E', 'E');

    Assertions.assertTrue(WordSearch.exist(board, "ABCCED"));
    Assertions.assertTrue(WordSearch.exist(board, "SEE"));
    Assertions.assertFalse(WordSearch.exist(board, "ABCB"));
    Assertions.assertFalse(WordSearch.exist(board, "Z"));
    Assertions.assertFalse(WordSearch.exist(board, "ABFDASA"));
  }

  @Test
  public void failed() {
    char[][] board = new char[0][];
    Assertions.assertFalse(WordSearch.exist(board, "Z"));
  }

  private char[] chars(char... chars) {
    return chars;
  }
}