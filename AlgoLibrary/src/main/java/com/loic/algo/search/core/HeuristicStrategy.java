package com.loic.algo.search.core;

public interface HeuristicStrategy<State> {
    public static final double ZERO = 0d;

    double heuristic(State state, int depth);
}
