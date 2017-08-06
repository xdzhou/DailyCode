package com.loic.solution;

import java.util.Scanner;
import java.util.function.Consumer;

public interface ScannerResolver<T> {
    void accept(Scanner in, Consumer<T> outputConsumer);
}
