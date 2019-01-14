package com.loic.leetcode.hard;

import java.util.Arrays;
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


  /**
   * This is one of the fastest Sudoku solvers I've ever written. It is compact enough - just 150 lines of C++ code with comments.
   * I thought it'd be interesting to share it, since it combines several techniques like reactive network update propagation and
   * backtracking with very aggressive pruning.
   * <p>
   * The algorithm is online - it starts with an empty board and as you add numbers to it, it starts solving the Sudoku.
   * <p>
   * Unlike in other solutions where you have bitmasks of allowed/disallowed values per row/column/square, this solution track bitmask
   * for every(!) cell, forming a set of constraints for the allowed values for each particular cell. Once a value is written into a cell,
   * new constraints are immediately propagated to row, column and 3x3 square of the cell. If during this process a value of other cell can
   * be unambiguously deduced - then the value is set, new constraints are propagated, so on.... You can think about this as an implicit reactive network of cells.
   * <p>
   * If we're lucky (and we'll be lucky for 19 of 20 of Sudokus published in magazines) then Sudoku is solved at the end (or even before!)
   * processing of the input.
   * <p>
   * Otherwise, there will be empty cells which have to be resolved. Algorithm uses backtracking for this purpose. To optimize it,
   * algorithm starts with the cell with the smallest ambiguity. This could be improved even further by using priority queue
   * (but it's not implemented here). Backtracking is more or less standard, however, at each step we guess the number,
   * the reactive update propagation comes back into play and it either quickly proves that the guess is unfeasible or
   * significantly prunes the remaining search space.
   * <p>
   * It's interesting to note, that in this case taking and restoring snapshots of the compact representation of the
   * state is faster than doing backtracking rollback by "undoing the moves".
   */

  public static void resolve(char[][] board) {
    Cell[][] cells = new Cell[9][9];
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        cells[i][j] = new Cell();
      }
    }
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        if (board[i][j] != '.') {
          setChar(cells, i, j, board[i][j]);
        }
      }
    }
    //
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        board[i][j] = cells[i][j].value;
      }
    }
  }

  private static void setChar(Cell[][] board, int row, int col, char c) {
    if (board[row][col].value == c) {
      return;
    }
    board[row][col].setNumber(c);

    for (int index = 0; index < 9; index++) {
      // check row
      Cell cell = board[row][index];
      if (index != col && cell.value == '.') {
        cell.possibleChars.remove(c);
        if (cell.possibleChars.size() == 1) {
          setChar(board, row, index, cell.possibleChars.iterator().next());
        }
      }
      // check column
      cell = board[index][col];
      if (index != row && cell.value == '.') {
        cell.possibleChars.remove(c);
        if (cell.possibleChars.size() == 1) {
          setChar(board, index, col, cell.possibleChars.iterator().next());
        }
      }
      // check box / grid / square
      int boxTopLeftRow = row / 3 * 3;
      int boxTopLeftCol = col / 3 * 3;
      int newRow = boxTopLeftRow + index / 3;
      int newCol = boxTopLeftCol + index % 3;
      cell = board[newRow][newCol];
      if (row != newRow && col != newCol && cell.value == '.') {
        cell.possibleChars.remove(c);
        if (cell.possibleChars.size() == 1) {
          setChar(board, newRow, newCol, cell.possibleChars.iterator().next());
        }
      }
    }
  }

  static List<Character> CHARS = Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9');

  private static class Cell {
    private char value = '.';
    private final Set<Character> possibleChars = new HashSet<>(CHARS);

    public void setNumber(char c) {
      value = c;
      possibleChars.clear();
    }
  }
}
