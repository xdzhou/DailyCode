package com.loic.algo.search.core;

public interface HeuristicStrategy<State> {
    double heuristic(State state, int depth);

    default boolean isWin(State state) {
        return false;
    }

    default boolean isLoss(State state) {
        return false;
    }
}
