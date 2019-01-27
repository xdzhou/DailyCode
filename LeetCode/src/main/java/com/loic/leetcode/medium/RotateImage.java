package com.loic.leetcode.medium;

public final class RotateImage {

  public static void rotate(int[][] matrix) {
    int N = matrix.length;
    // line i will be column N-1-i
    // column j will be line j
    int lineMax = N % 2 == 0 ? N / 2 : N / 2 + 1;
    for (int line = 0; line < lineMax; line++) {
      for (int col = line; col < N - 1 - line; col++) {
        int temp = matrix[line][col];
        matrix[line][col] = matrix[N - 1 - col][line];
        matrix[N - 1 - col][line] = matrix[N - 1 - line][N - 1 - col];
        matrix[N - 1 - line][N - 1 - col] = matrix[col][N - 1 - line];
        matrix[col][N - 1 - line] = temp;
      }
    }
  }
}
