package com.loic.solution;

import java.util.List;
import java.util.function.BiFunction;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;

public class BiSolutionChecker<T, K, E> {
  private final BiSolutionProvider<T, K, E> solutionProvider;

  private BiSolutionChecker(BiSolutionProvider<T, K, E> solutionProvider) {
    this.solutionProvider = requireNonNull(solutionProvider);
  }

  public static <T, K, E> BiSolutionChecker<T, K, E> create(BiSolutionProvider<T, K, E> solutionProvider) {
    return new BiSolutionChecker<>(solutionProvider);
  }

  public BiSolutionChecker<T, K, E> check(T input1, K input2, E output) {
    for (BiFunction<T, K, E> solution : solutionProvider.solutions()) {
      Object curOut = solution.apply(input1, input2);
      if (output.getClass().isArray()) {
        assertArrayEquals((Object[]) curOut, (Object[]) output);
      } else {
        assertEquals(curOut, output);
      }
    }
    return this;
  }

  public BiSolutionChecker<T, K, E> checkInput(T input1, K input2) {
    List<BiFunction<T, K, E>> list = solutionProvider.solutions();
    assertTrue(list.size() > 1);
    E output = list.get(0).apply(input1, input2);
    for (int i = 1; i < list.size(); i++) {
      assertEquals(list.get(i).apply(input1, input2), output);
    }
    return this;
  }
}
