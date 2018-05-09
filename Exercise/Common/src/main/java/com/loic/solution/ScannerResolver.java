package com.loic.solution;

import java.util.Scanner;

public interface ScannerResolver<T> {
  T accept(Scanner in);
}
