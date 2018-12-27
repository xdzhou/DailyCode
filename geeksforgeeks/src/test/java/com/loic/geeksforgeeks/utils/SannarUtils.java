package com.loic.geeksforgeeks.utils;

import java.util.Scanner;

public class SannarUtils {

  private void main() {
    Scanner scanner = new Scanner(System.in);
    int caseCount = scanner.nextInt();
    for (int i = 0; i < caseCount; i++) {
      int N = scanner.nextInt();

      int[] nums = new int[N];
      for (int index = 0; index < N; index++) {
        nums[index] = scanner.nextInt();
      }

      System.out.println();
    }
  }
}
