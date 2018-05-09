package com.loic.codinGame.match;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class RankFile {
  private Comparator<Integer[]> mComparator = new Comparator<Integer[]>() {
    @Override
    public int compare(Integer[] o1, Integer[] o2) {
      int size = o1.length;
      int result = 0;
      for (int i = 0; i < size && result == 0; i++) {
        result = o1[i] - o2[i];
      }
      return result;
    }
  };

  public static void main(String[] args) {
    new RankFile().start();
  }

  private void start() {
    Scanner in = new Scanner(System.in, "UTF-8");
    int count = in.nextInt();
    in.nextLine();
    for (int i = 0; i < count; i++) {
      int size = in.nextInt();
      in.nextLine();
      Integer[][] table = new Integer[2 * size - 1][size];
      for (int j = 0; j < 2 * size - 1; j++) {
        for (int k = 0; k < size; k++) {
          table[j][k] = in.nextInt();
        }
      }
      treat(table, size);
    }
    in.close();
  }

  private void treat(Integer[][] table, int size) {
    Arrays.sort(table, mComparator);
    int len = 2 * size - 1;
    int[][] temp = new int[size][size];
    boolean[] flags = new boolean[size];
    //fill row0
    flags[0] = true;
    applyRow(table, temp, 0, 0);

    if (table[0][0].equals(table[1][0])) {
      applyColumn(table, temp, 2, 0);
      boolean appRow = true;
      if (temp[0][size - 1] == table[len - 1][0]) {
        applyColumn(table, temp, len - 1, size - 1);
        appRow = false;
      } else {
        flags[len - 1] = true;
        applyRow(table, temp, len - 1, size - 1);
      }
      if (table[len - 2][0].equals(table[len - 1][0])) {
        if (appRow) {
          applyColumn(table, temp, len - 2, size - 1);
        } else {
          flags[len - 2] = true;
          applyRow(table, temp, len - 2, size - 1);
        }
      }
    } else {
      if (temp[0][size - 1] == table[len - 2][0]) {
        applyColumn(table, temp, len - 2, size - 1);
        flags[len - 1] = true;
        applyRow(table, temp, len - 1, size - 1);
      } else {
        applyColumn(table, temp, len - 1, size - 1);
        flags[len - 2] = true;
        applyRow(table, temp, len - 2, size - 1);
      }
    }
    System.out.println("Flags : " + Arrays.toString(flags));
  }

  private void applyRow(Integer[][] table, int[][] temp, int num, int row) {
    for (int i = 0; i < temp.length; i++) {
      temp[row][i] = table[num][i];
    }
  }

  private void applyColumn(Integer[][] table, int[][] temp, int num, int column) {
    for (int i = 0; i < temp.length; i++) {
      temp[i][column] = table[num][i];
    }
  }
}
