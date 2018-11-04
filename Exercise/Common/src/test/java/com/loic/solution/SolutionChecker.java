package com.loic.solution;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;

public class SolutionChecker<T, E> {
  private final SolutionProvider<T, E> solutionProvider;

  private SolutionChecker(SolutionProvider<T, E> solutionProvider) {
    this.solutionProvider = requireNonNull(solutionProvider);
  }

  public static <T, E> SolutionChecker<T, E> create(SolutionProvider<T, E> solutionProvider) {
    return new SolutionChecker<>(solutionProvider);
  }

  public SolutionChecker<T, E> check(T input, E output) {
    for (Function<T, E> solution : solutionProvider.solutions()) {
      assertEquals(solution.apply(input), output);
    }
    return this;
  }

  public SolutionChecker<T, E> checkInput(T input) {
    List<Function<T, E>> list = solutionProvider.solutions();
    assertTrue(list.size() > 1);
    E output = list.get(0).apply(input);
    for (int i = 1; i < list.size(); i++) {
      if (output.getClass().isArray()) {
        assertArrayEquals((Object[]) list.get(i).apply(input), (Object[]) output);
      } else {
        assertEquals(list.get(i).apply(input), output);
      }
    }
    return this;
  }

  public SolutionChecker<T, E> check(T input, BiConsumer<T, E> consumer) {
    for (Function<T, E> solution : solutionProvider.solutions()) {
      consumer.accept(input, solution.apply(input));
    }
    return this;
  }
}
