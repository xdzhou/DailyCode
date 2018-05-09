package com.loic.codejam.training;

import com.loic.solution.ScannerResolver;

import java.util.Scanner;

public class ReverseWords implements ScannerResolver<String> {

  @Override
  public String accept(Scanner in) {
    String s = in.nextLine();
    // System.out.println(s);
    String[] items = s.split(" ");
    StringBuffer sb = new StringBuffer();
    for (int j = items.length - 1; j >= 0; j--) {
      sb.append(items[j]).append(' ');
    }
    return sb.toString();
  }
}
