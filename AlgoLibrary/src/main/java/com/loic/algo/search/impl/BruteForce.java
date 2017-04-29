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
    private final Map<State, Pair<Transition, State>> transitionMap = Maps.newHashMap();

    @Override
    public SearchPath find(State root, int maxDeep) {
        Objects.requireNonNull(root, "Root state is mandatory");
        Preconditions.checkState(maxDeep > 0, "Max deep must bigger than 0");

        process(root, maxDeep);

        List<Transition> transitions = Lists.newArrayList();
        Pair<Transition, State> pair = transitionMap.get(root);
        while (pair != null) {
            transitions.add(pair.first());
            pair = transitionMap.get(pair.second());
        }

        transitionMap.clear();
        return new SearchPath(transitions);
    }

    private double process(State state, int deep) {
        if (deep == 0 || state.nextPossibleTransitions().isEmpty()) {
            return state.heuristic();
        } else {
            double best = Double.MIN_VALUE;
            Transition bestTransition = null;
            State bestChildState = null;
            for(Transition transition : state.nextPossibleTransitions()) {
                State nextState = state.apply(transition);
                double fitness = process(nextState, deep - 1);
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
