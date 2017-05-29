package com.loic.algo.search.impl;

import static java.util.Objects.requireNonNull;

import java.util.Optional;
import java.util.Set;

import com.loic.algo.common.Pair;
import com.loic.algo.search.TreeSearchUtils;
import com.loic.algo.search.core.SearchParam;
import com.loic.algo.search.core.TreeSearch;

public class BruteForce implements TreeSearch {

    @Override
    public <Trans, State> Optional<Trans> find(State root, SearchParam<Trans, State> param) {
        requireNonNull(root, "Root state is mandatory");
        requireNonNull(param, "SearchParam is mandatory");

        Optional<Trans> next = TreeSearchUtils.nextTrans(root, param.transitionStrategy());
        if (next != null) return next;

        Pair<Trans, Double> best = process(param, root, 0);

        return Optional.ofNullable(best.first());
    }

    private <Trans, State> Pair<Trans, Double> process(SearchParam<Trans, State> param, State state, int depth) {
        if (depth > param.getMaxDepth()) {
            return Pair.of(null, param.heuristicStrategy().heuristic(state, depth));
        } else {
            Pair<Trans, Double> best = null;
            Set<Trans> transitions = param.transitionStrategy().generate(state);
            for(Trans transition : transitions) {
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
