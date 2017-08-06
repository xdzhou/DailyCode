package com.loic.codinGame;

import java.util.Scanner;

import com.loic.solution.ScannerResolver;

public interface CodinGameResolver<T> extends ScannerResolver<T> {

    default void before(Scanner in) {
    }
}
