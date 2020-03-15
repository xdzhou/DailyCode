package com.loic.optimization.knapsack;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.loic.optimization.MaxIterationException;

/**
 * limited discrepancy search
 */
public class LDSKnapsackResolver implements KnapsackResolver {
  private static final Logger LOGGER = Logger.getLogger(LDSKnapsackResolver.class.getName());
  private final int maxIteration;
  private final Comparator<Treasure> comparator;

  public LDSKnapsackResolver(int maxIteration) {
    this.maxIteration = maxIteration;
    this.comparator = null;
  }

  public LDSKnapsackResolver(int maxIteration, Comparator<Treasure> comparator) {
    this.maxIteration = maxIteration;
    this.comparator = comparator;
  }

  @Override
  public Set<Treasure> resolve(List<Treasure> items, int capacity) {
    Stream<Treasure> streamItems = items.stream()
      .filter(t -> t.weight() <= capacity)
      .filter(t -> t.value() > 0);
    if (comparator != null) {
      streamItems = streamItems.sorted(comparator);
    }
    List<Treasure> sortedItems = streamItems.collect(Collectors.toList());
    return resolveSortedList(sortedItems, capacity).treasures;
  }

  private Result resolveSortedList(List<Treasure> sortedItems, int capacity) {
    List<Treasure> items = sortedItems;
    Result[] best = new Result[1];
    int[] iterationCount = new int[1];
    try {
      while (!items.isEmpty()) {
        Result initial = search(items, capacity, Collections.emptySet());
        best[0] = better(best[0], initial);
        int size = initial.treasures.size();
        List<Treasure> tmp = items;
        for (int skipCount = 1; skipCount < size; skipCount++) {
          Set<Integer> skippedItems = new HashSet<>();
          findSkip(skippedItems, 0, size - 1, skipCount, skip -> {
            iterationCount[0]++;
            if (iterationCount[0] > maxIteration) {
              throw new MaxIterationException(maxIteration);
            }
            Result r = search(tmp, capacity, skip);
            best[0] = better(best[0], r);
          });
        }
        items = items.subList(size, items.size());
      }
    } catch (MaxIterationException e) {
      LOGGER.info("max iteration reached...");
    }
    return best[0];
  }

  private Result better(Result r1, Result r2) {
    int value1 = r1 == null ? 0 : r1.sumValue;
    int value2 = r2 == null ? 0 : r2.sumValue;
    return value1 > value2 ? r1 : r2;
  }

  // find skipped index from [from : to]
  private void findSkip(Set<Integer> skippedItems, int from, int to, int skipCount, Consumer<Set<Integer>> consumer) {
    if (0 == skipCount) {
      consumer.accept(skippedItems);
    } else if (to - from + 1 >= skipCount) {
      for (int i = from; i <= to - skipCount + 1; i++) {
        skippedItems.add(i);
        findSkip(skippedItems, i + 1, to, skipCount - 1, consumer);
        skippedItems.remove(i);
      }
    }
  }

  private Result search(List<Treasure> items, int capacity, Set<Integer> skippedItems) {
    int remainCapacity = capacity;
    Set<Treasure> result = new HashSet<>();
    for (int i = 0; i < items.size(); i++) {
      if (!skippedItems.contains(i)) {
        Treasure t = items.get(i);
        if (t.weight() <= remainCapacity) {
          result.add(t);
          remainCapacity -= t.weight();
        }
      }
    }
    return new Result(result);
  }

  private static final class Result {
    private final Set<Treasure> treasures;
    private final int sumValue;

    private Result(Set<Treasure> treasures) {
      this.treasures = treasures;
      sumValue = treasures.stream().mapToInt(Treasure::value).sum();
    }
  }
}
