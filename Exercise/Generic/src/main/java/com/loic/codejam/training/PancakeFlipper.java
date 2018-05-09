package com.loic.codejam.training;

import com.loic.solution.ScannerResolver;

import java.util.Scanner;

/**
 * https://code.google.com/codejam/contest/3264486/dashboard
 */
public class PancakeFlipper implements ScannerResolver<String> {

  @Override
  public String accept(Scanner in) {
    String cakes = in.next();
    int count = in.nextInt();

    int nb = 0;
    boolean[] flags = new boolean[cakes.length()];
    for (int i = 0; i < flags.length; i++) {
      flags[i] = cakes.charAt(i) == '+';
    }
    for (int i = 0; i < flags.length; i++) {
      if (!flags[i]) {
        if (flags.length - i >= count) {
          nb++;
          for (int j = 0; j < count; j++) {
            flags[i + j] = !flags[i + j];
          }
        } else {
          return "IMPOSSIBLE";
        }
      }
    }
    return Integer.toString(nb);
  }
}
