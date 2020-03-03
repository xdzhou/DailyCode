package com.loic.optimization.knapsack;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GreedyKnapsackResolver implements KnapsackResolver {
  private final Comparator<Treasure> comparator;

  public GreedyKnapsackResolver(Comparator<Treasure> comparator) {
    this.comparator = comparator;
  }

  @Override
  public Set<Treasure> resolve(List<Treasure> items, int capacity) {
    Set<Treasure> result = new HashSet<>();
    List<Treasure> sortedItems = items.stream()
      .filter(t -> t.weight() <= capacity)
      .filter(t -> t.value() > 0)
      .sorted(comparator).collect(Collectors.toList());
    int remainCapacity = capacity;
    for (Treasure t : sortedItems) {
      if (t.weight() <= remainCapacity) {
        result.add(t);
        remainCapacity -= t.weight();
      }
    }
    return result;
  }
}
