package com.loic.algo.search.core;

public interface ApplyStrategy<Trans, State> {
    State apply(State state, Trans trans);
}
