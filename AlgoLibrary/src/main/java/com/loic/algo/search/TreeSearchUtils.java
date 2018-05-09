package com.loic.algo.search;

import com.loic.algo.search.core.TransitionStrategy;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class TreeSearchUtils {
  private TreeSearchUtils() {
  }

  public static <E> List<E> asStringSort(Set<E> sets) {
    return sets.stream()
        .sorted(Comparator.comparing(Object::toString))
        .collect(Collectors.toList());
  }

  //FIXME should not supposed to return null
  public static <State, Trans> Optional<Trans> nextTrans(State state, TransitionStrategy<State, Trans> strategy) {
    Set<Trans> trans = strategy.generate(state);
    if (trans.isEmpty()) {
      return Optional.empty();
    } else if (trans.size() == 1) {
      return Optional.of(trans.iterator().next());
    }
    return null;
  }
}
