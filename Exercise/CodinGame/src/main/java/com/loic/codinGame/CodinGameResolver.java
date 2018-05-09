package com.loic.codinGame;

import com.loic.solution.ScannerResolver;

import java.util.Scanner;

public interface CodinGameResolver<T> extends ScannerResolver<T> {

  default void before(Scanner in) {
  }
}
