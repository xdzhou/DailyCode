package com.loic.algo.search.core;

import java.util.Set;

public interface State<Trans extends Transition> {

    double heuristic();

    default boolean isTerminal() {
        double fitness = heuristic();
        return Double.compare(0, fitness) == 0 || nextPossibleTransitions().isEmpty();
    }

    State apply(Trans transition);
    Set<Trans> nextPossibleTransitions();

}
