package com.loic.algo.search.core;

public interface HeuristicStrategy<State> {
    double ZERO = 0d;

    double heuristic(State state, int depth);
}
