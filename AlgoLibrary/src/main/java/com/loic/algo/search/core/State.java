package com.loic.algo.search.core;

import java.util.List;

import com.google.common.base.Preconditions;

public interface State<Trans extends Transition> {

    double heuristic();

    default boolean isTerminal() {
        double fitness = heuristic();
        Preconditions.checkState(fitness >= 0 && fitness <= 1, "Fitness should between 0 and 1");
        return Double.compare(0, fitness) == 0 || nextPossibleTransitions().isEmpty();
    }

    State apply(Trans transition);
    List<Trans> nextPossibleTransitions();

}
