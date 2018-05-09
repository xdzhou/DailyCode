package com.loic.codejam;

import com.loic.solution.ScannerResolver;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.BiConsumer;

import static com.google.common.base.Preconditions.checkState;
import static java.util.Objects.requireNonNull;

public class JamExecutor<T> {
  private final ScannerResolver<T> resolver;

  public JamExecutor(ScannerResolver<T> resolver) {
    this.resolver = requireNonNull(resolver);
  }

  public void execute(File inputFileFolder) {
    requireNonNull(inputFileFolder);
    checkState(inputFileFolder.isDirectory());

    Arrays.stream(inputFileFolder.listFiles())
        .filter(File::isFile)
        .filter(file -> file.getName().endsWith("in"))
        .forEach(this::process);
  }

  private void process(File input) {
    try (PrintWriter writer = new PrintWriter(input.getAbsolutePath().replace("in", "out"), "UTF-8")) {
      Scanner in = new Scanner(input, "UTF-8");
      process(in, (index, result) -> writer.println("Case #" + (index + 1) + ": " + result));
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

  private void process(Scanner in, BiConsumer<Integer, T> resultConsumer) {
    int count = in.nextInt();
    in.nextLine();
    for (int i = 0; i < count; i++) {
      resultConsumer.accept(i, resolver.accept(in));
    }
    in.close();
  }
}
