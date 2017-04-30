package com.loic.algo.search.impl;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.loic.algo.common.Pair;
import com.loic.algo.search.core.PathFinder;
import com.loic.algo.search.core.SearchPath;
import com.loic.algo.search.core.State;
import com.loic.algo.search.core.Transition;

public class BruteForce implements PathFinder {

    @Override
    public <Trans extends Transition> SearchPath<Trans> find(State<Trans> root, int maxDeep) {
        Objects.requireNonNull(root, "Root state is mandatory");
        Preconditions.checkState(maxDeep > 0, "Max deep must bigger than 0");

        Map<State<Trans>, Pair<Trans, State>> transitionMap = Maps.newHashMap();
        process(root, maxDeep, transitionMap);

        List<Trans> transitions = Lists.newArrayList();
        Pair<Trans, State> pair = transitionMap.get(root);
        while (pair != null) {
            transitions.add(pair.first());
            pair = transitionMap.get(pair.second());
        }
        return new SearchPath<>(transitions);
    }

    private <Trans extends Transition> double process(State<Trans> state, int deep, Map<State<Trans>, Pair<Trans, State>> transitionMap) {
        if (deep == 0 || state.isTerminal()) {
            return state.heuristic();
        } else {
            double best = Double.MIN_VALUE;
            Trans bestTransition = null;
            State bestChildState = null;
            for(Trans transition : state.nextPossibleTransitions()) {
                State nextState = state.apply(transition);
                double fitness = process(nextState, deep - 1, transitionMap);
                if (fitness > best) {
                    best = fitness;
                    bestTransition = transition;
                    bestChildState = nextState;
                }
            }
            transitionMap.put(state, Pair.of(bestTransition, bestChildState));
            return best;
        }
    }
}
