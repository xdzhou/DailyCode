package com.loic.algo.search.core;

public interface HeuristicStrategy<State> {
    double heuristic(State state, int depth);
}
