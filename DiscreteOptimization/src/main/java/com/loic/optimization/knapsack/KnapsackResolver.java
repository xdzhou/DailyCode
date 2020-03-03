package com.loic.optimization.knapsack;

import java.util.List;
import java.util.Set;

public interface KnapsackResolver {
  Set<Treasure> resolve(List<Treasure> items, int capacity);
}
