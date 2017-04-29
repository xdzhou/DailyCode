package com.loic.algo.search.core;

import java.util.List;

public interface State {
    double heuristic();
    State apply(Transition transition);
    List<Transition> nextPossibleTransitions();
}
