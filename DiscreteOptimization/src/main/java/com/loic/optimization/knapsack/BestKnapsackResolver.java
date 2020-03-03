package com.loic.optimization.knapsack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class BestKnapsackResolver implements KnapsackResolver {
  private final List<KnapsackResolver> resolvers;

  public BestKnapsackResolver(List<KnapsackResolver> resolvers) {
    this.resolvers = new ArrayList<>(resolvers);
  }

  public BestKnapsackResolver() {
    this.resolvers = Arrays.asList(
      new GreedyKnapsackResolver(Comparator.comparingDouble(Treasure::density).reversed()),
      new GreedyKnapsackResolver(Comparator.comparingDouble(Treasure::weight).reversed()),
      new GreedyKnapsackResolver(Comparator.comparingDouble(Treasure::value).reversed()),
      new BranchBoundKnapsackResolver(10_000)
    );
  }

  @Override
  public Set<Treasure> resolve(List<Treasure> items, int capacity) {
    return resolvers.stream()
      .map(r -> r.resolve(items, capacity))
      .max(Comparator.comparingInt(this::sumValue))
      .orElse(Collections.emptySet());
  }

  private int sumValue(Set<Treasure> treasures) {
    return treasures.stream().mapToInt(Treasure::value).sum();
  }
}
