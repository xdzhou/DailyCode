package com.loic.leetcode.hard;

/**
 * 980. Unique Paths III
 * <p>
 * You are given an m x n integer array grid where grid[i][j] could be:
 * <p>
 * 1 representing the starting square. There is exactly one starting square.
 * 2 representing the ending square. There is exactly one ending square.
 * 0 representing empty squares we can walk over.
 * -1 representing obstacles that we cannot walk over.
 * Return the number of 4-directional walks from the starting square to the ending square,
 * that walk over every non-obstacle square exactly once.
 * <p>
 * Example 1:
 * Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
 * Output: 2
 * Explanation: We have the following two paths:
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
 * 2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
 * <p>
 * Example 2:
 * Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
 * Output: 4
 * Explanation: We have the following four paths:
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
 * 2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
 * 3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
 * 4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
 * <p>
 * Example 3:
 * Input: grid = [[0,1],[2,0]]
 * Output: 0
 * Explanation: There is no path that walks over every empty square exactly once.
 * Note that the starting and ending square can be anywhere in the grid.
 * <p>
 * Constraints:
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 20
 * 1 <= m * n <= 20
 * -1 <= grid[i][j] <= 2
 * There is exactly one starting cell and one ending cell.
 */
public class UniquePathsIII {

  public static int resolve(int[][] grid) {
    int row = 0, col = 0, total = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == 1) {
          row = i;
          col = j;
        } else if (grid[i][j] == 0) {
          total++;
        }
      }
    }
    return dfs(row, col, 0, total + 1, grid);
  }

  private static int dfs(int row, int col, int stepNb, int total, int[][] grid) {
    if (grid[row][col] == 2) {
      return stepNb == total ? 1 : 0;
    }
    grid[row][col] = -1; // mark as walk over
    stepNb++;
    int result = 0;
    // to top square
    if (row - 1 >= 0 && grid[row - 1][col] != -1) {
      result += dfs(row - 1, col, stepNb, total, grid);
    }
    // to bottom square
    if (row + 1 < grid.length && grid[row + 1][col] != -1) {
      result += dfs(row + 1, col, stepNb, total, grid);
    }
    // to left square
    if (col - 1 >= 0 && grid[row][col - 1] != -1) {
      result += dfs(row, col - 1, stepNb, total, grid);
    }
    // to right square
    if (col + 1 < grid[0].length && grid[row][col + 1] != -1) {
      result += dfs(row, col + 1, stepNb, total, grid);
    }
    grid[row][col] = 0; // unmark
    stepNb--;
    return result;
  }
}
