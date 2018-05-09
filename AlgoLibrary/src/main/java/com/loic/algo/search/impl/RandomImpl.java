package com.loic.algo.search.impl;

import com.loic.algo.search.TreeSearchUtils;
import com.loic.algo.search.core.SearchParam;
import com.loic.algo.search.core.TreeSearch;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import static java.util.Objects.requireNonNull;

public class RandomImpl implements TreeSearch {
  private static final Random RANDOM = new Random();

  @Override
  public <State, Trans> Optional<Trans> find(State root, SearchParam<State, Trans> param) {
    requireNonNull(root, "Root state is mandatory");
    requireNonNull(param, "SearchParam is mandatory");

    Set<Trans> child = param.transitionStrategy().generate(root);
    if (child.isEmpty()) {
      return Optional.empty();
    } else if (child.size() == 1) {
      return Optional.of(child.iterator().next());
    } else {
      List<Trans> list = TreeSearchUtils.asStringSort(child);
      return Optional.of(list.get(RANDOM.nextInt(list.size())));
    }
  }
}
