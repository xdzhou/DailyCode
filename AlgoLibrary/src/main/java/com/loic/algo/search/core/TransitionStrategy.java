package com.loic.algo.search.core;

import java.util.Set;

public interface TransitionStrategy<Trans, State> {

    Set<Trans> generate(State state);
}
