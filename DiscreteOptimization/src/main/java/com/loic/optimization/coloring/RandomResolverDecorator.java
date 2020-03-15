package com.loic.optimization.coloring;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.stream.IntStream;

public class RandomResolverDecorator implements ColoringResolver {
  private static final Random RANDOM = new Random(111);
  private final ColoringResolver delegate;
  private final int timeoutMs;

  public RandomResolverDecorator(ColoringResolver delegate, int timeoutMs) {
    this.delegate = delegate;
    this.timeoutMs = timeoutMs;
  }

  @Override
  public Map<Vertex, Integer> resolve(List<Vertex> vertices) {
    List<Vertex> tmp = new ArrayList<>(vertices);
    long maxMs = System.currentTimeMillis() + timeoutMs;
    return IntStream.range(0, timeoutMs)
      .mapToObj(i -> {
        if (System.currentTimeMillis() <= maxMs) {
          Collections.shuffle(tmp, RANDOM);
          return delegate.resolve(tmp);
        } else {
          return null;
        }
      })
      .filter(Objects::nonNull)
      .min(Comparator.comparingInt(BestColoringResolver::colorCount))
      .orElse(Collections.emptyMap());
  }
}
