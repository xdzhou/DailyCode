package com.loic.algo.search.core;

public interface ApplyStrategy<State, Trans> {
  State apply(State state, Trans trans);
}
