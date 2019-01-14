package com.loic.leetcode.hard;

import java.util.stream.IntStream;

import com.loic.leetcode.medium.ValidSudoku;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SudokuSolverTest {

  @Test
  void test() {
    char[][] board = goodBoard();
    SudokuSolver.bruteForce(board);

    Assertions.assertTrue(noEmpty(board));
    Assertions.assertTrue(ValidSudoku.encodeResolve(board));
  }

  private boolean noEmpty(char[][] board) {
    return IntStream.range(0, 81)
      .allMatch(index -> {
        int row = index / 9;
        int col = index % 9;
        return board[row][col] != '.';
      });
  }

  private char[][] goodBoard() {
    return new char[][]{
      {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
      {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
      {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
      {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
      {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
      {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
      {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
      {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
      {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
    };
  }
}