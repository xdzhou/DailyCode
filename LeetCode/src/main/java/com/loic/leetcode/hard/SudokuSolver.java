package com.loic.leetcode.hard;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 37. Sudoku Solver
 * https://leetcode.com/problems/sudoku-solver/
 * <p>
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * Empty cells are indicated by the character '.'
 */
public final class SudokuSolver {

  public static void bruteForce(char[][] board) {
    Set<Integer> encodeSet = new HashSet<>();
    List<Integer> emptyCells = new LinkedList<>();
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        char c = board[i][j];
        if (c != '.') {
          int num = c - '1';
          int grid = i / 3 * 3 + j / 3;
          encodeSet.add(i * 9 + num);
          encodeSet.add(81 + j * 9 + num);
          encodeSet.add(162 + grid * 9 + num);
        } else {
          emptyCells.add(i * 9 + j);
        }
      }
    }

    bruteForceFill(encodeSet, emptyCells, board);
  }

  private static boolean bruteForceFill(Set<Integer> encodeSet, List<Integer> emptyCells, char[][] board) {
    if (emptyCells.isEmpty()) {
      return true;
    } else {
      int cell = emptyCells.remove(0);
      int row = cell / 9;
      int col = cell % 9;
      int grid = row / 3 * 3 + col / 3;
      for (int num = 0; num < 9; num++) {
        int rowPosition = row * 9 + num;
        int colPosition = 81 + col * 9 + num;
        int gridPosition = 162 + grid * 9 + num;
        if (!encodeSet.contains(rowPosition)
          && !encodeSet.contains(colPosition)
          && !encodeSet.contains(gridPosition)) {

          encodeSet.add(rowPosition);
          encodeSet.add(colPosition);
          encodeSet.add(gridPosition);

          if (bruteForceFill(encodeSet, emptyCells, board)) {
            // char '0' is 48 for decimal
            board[row][col] = (char) (num + 1 + 48);
            return true;
          }

          encodeSet.remove(rowPosition);
          encodeSet.remove(colPosition);
          encodeSet.remove(gridPosition);
        }
      }
      // the cell has no valid number to fill in this stage, need to be re-filled
      emptyCells.add(0, cell);
      return false;
    }
  }
}
