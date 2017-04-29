package com.loic.algo.search.core;

public interface PathFinder {
    <Trans extends Transition> SearchPath<Trans> find(State<Trans> root, int maxDeep);
}
