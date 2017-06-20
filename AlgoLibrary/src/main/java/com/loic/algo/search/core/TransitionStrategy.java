package com.loic.algo.search.core;

import java.util.Set;

public interface TransitionStrategy<State, Trans> {

    Set<Trans> generate(State state);
}
