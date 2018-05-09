package com.loic.algo.search.impl;

import com.loic.algo.common.Pair;
import com.loic.algo.search.TreeSearchUtils;
import com.loic.algo.search.core.SearchParam;
import com.loic.algo.search.core.TreeSearch;

import java.util.Optional;
import java.util.Set;

import static java.util.Objects.requireNonNull;

/**
 * KeyWord : game theory, decision theory, DFS
 */
public class MinimaxAlphaBeta implements TreeSearch {

  @Override
  public <State, Trans> Optional<Trans> find(State root, SearchParam<State, Trans> param) {
    requireNonNull(root, "Root state is mandatory");
    requireNonNull(param, "SearchParam is mandatory");

    Optional<Trans> next = TreeSearchUtils.nextTrans(root, param.transitionStrategy());
    if (next != null) {
      return next;
    }

    Pair<Trans, Double> result = alphaBeta(param, root, 0, Double.NEGATIVE_INFINITY, Double.MAX_VALUE, true);

    return Optional.ofNullable(result.first());
  }

  private <State, Trans> Pair<Trans, Double> alphaBeta(SearchParam<State, Trans> param, State state, int depth, double alpha, double beta, boolean maxPlayer) {
    Set<Trans> transitions = param.transitionStrategy().generate(state);

    if (depth >= param.getMaxDepth() || transitions.isEmpty()) {
      double fitness = param.heuristicStrategy().heuristic(state, depth);
      return Pair.of(null, fitness);
    }

    if (maxPlayer) {
      double best = Double.NEGATIVE_INFINITY;
      Trans bestTrans = null;
      for (Trans trans : transitions) {
        Pair<Trans, Double> childValue = alphaBeta(param, param.applyStrategy().apply(state, trans), depth + 1, alpha, beta, false);
        if (childValue.second() > best) {
          best = childValue.second();
          bestTrans = trans;
        }
        alpha = Math.max(alpha, best);
        if (beta <= alpha) {
          break;
        }
      }
      return Pair.of(bestTrans, best);
    } else {
      double best = Double.MAX_VALUE;
      Trans bestTrans = null;
      for (Trans trans : transitions) {
        Pair<Trans, Double> childValue = alphaBeta(param, param.applyStrategy().apply(state, trans), depth + 1, alpha, beta, true);
        if (childValue.second() < best) {
          best = childValue.second();
          bestTrans = trans;
        }
        beta = Math.min(beta, best);
        if (beta <= alpha) {
          break;
        }
      }
      return Pair.of(bestTrans, best);
    }
  }
}
