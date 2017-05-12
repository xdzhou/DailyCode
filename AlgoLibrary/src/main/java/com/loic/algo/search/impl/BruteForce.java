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

    private <Trans extends Transition> FitnessAndDepth process(State<Trans> state, int deep, Map<State<Trans>, Pair<Trans, State>> transitionMap) {
        if (deep <= 0 || state.isTerminal()) {
            return new FitnessAndDepth(state.heuristic(), 0);
        } else {
            //double best = Double.NEGATIVE_INFINITY;
            FitnessAndDepth best = null;
            Trans bestTransition = null;
            State bestChildState = null;
            for(Trans transition : state.nextPossibleTransitions()) {
                State nextState = state.apply(transition);
                FitnessAndDepth preResult = process(nextState, deep - 1, transitionMap);
                if (best == null || preResult.compareTo(best) > 0) {
                    best = preResult;
                    bestTransition = transition;
                    bestChildState = nextState;
                }
            }
            transitionMap.put(state, Pair.of(bestTransition, bestChildState));
            return new FitnessAndDepth(best.fitness, best.depth + 1);
        }
    }

    private static class FitnessAndDepth implements Comparable<FitnessAndDepth> {
        private double fitness;
        private int depth;

        public FitnessAndDepth(double fitness, int depth) {
            this.fitness = fitness;
            this.depth = depth;
        }

        @Override
        public int compareTo(FitnessAndDepth o) {
            int fitnessCompare = Double.compare(fitness, o.fitness);
            return fitnessCompare == 0 ? Integer.compare(o.depth, depth) : fitnessCompare;
        }
    }
}
