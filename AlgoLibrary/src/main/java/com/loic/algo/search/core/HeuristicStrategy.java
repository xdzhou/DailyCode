package com.loic.algo.search.core;

public interface HeuristicStrategy<State> {
    double heuristic(State state, int depth);

    default Winess winess(State state) {
        return Winess.UNKNOWN;
    }

    enum Winess {
        WIN, LOSS, UNKNOWN
    }
}
