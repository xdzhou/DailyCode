package com.loic.optimization.knapsack;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

public class BestKnapsackResolver implements KnapsackResolver {
  private static final Logger LOGGER = Logger.getLogger(BestKnapsackResolver.class.getName());
  private final Map<String, KnapsackResolver> resolvers;

  public BestKnapsackResolver(Map<String, KnapsackResolver> resolvers) {
    this.resolvers = new HashMap<>(resolvers);
  }

  @Override
  public Set<Treasure> resolve(List<Treasure> items, int capacity) {
    return resolvers.entrySet().stream()
      .map(e -> {
        Set<Treasure> result = e.getValue().resolve(items, capacity);
        LOGGER.info(e.getKey() + " : " + sumValue(result));
        return result;
      })
      .max(Comparator.comparingInt(this::sumValue))
      .orElse(Collections.emptySet());
  }

  private int sumValue(Set<Treasure> treasures) {
    return treasures.stream().mapToInt(Treasure::value).sum();
  }
}
