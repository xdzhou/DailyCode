package com.loic.algo.search.impl;

import com.loic.algo.common.Pair;
import com.loic.algo.search.TreeSearchUtils;
import com.loic.algo.search.core.SearchParam;
import com.loic.algo.search.core.TreeSearch;

import java.util.Optional;
import java.util.Set;

import static java.util.Objects.requireNonNull;

public class BruteForce implements TreeSearch {

  @Override
  public <State, Trans> Optional<Trans> find(State root, SearchParam<State, Trans> param) {
    requireNonNull(root, "Root state is mandatory");
    requireNonNull(param, "SearchParam is mandatory");

    Optional<Trans> next = TreeSearchUtils.nextTrans(root, param.transitionStrategy());
    if (next != null) {
      return next;
    }

    Pair<Trans, Double> best = process(param, root, 0);

    return Optional.ofNullable(best.first());
  }

  private <State, Trans> Pair<Trans, Double> process(SearchParam<State, Trans> param, State state, int depth) {
    if (depth > param.getMaxDepth()) {
      return Pair.of(null, param.heuristicStrategy().heuristic(state, depth));
    } else {
      Pair<Trans, Double> best = null;
      Set<Trans> transitions = param.transitionStrategy().generate(state);
      for (Trans transition : transitions) {
        State nextState = param.applyStrategy().apply(state, transition);
        Pair<Trans, Double> preResult = process(param, nextState, depth + 1);
        if (best == null || Double.compare(preResult.second(), best.second()) > 0) {
          best = Pair.of(transition, preResult.second());
        }
      }
      if (best == null) {
        best = Pair.of(null, param.heuristicStrategy().heuristic(state, depth));
      }
      return best;
    }
  }
}
