package com.loic.algo.search.core;

public interface PathFinder {
    SearchPath find(State root, int maxDeep);
}
