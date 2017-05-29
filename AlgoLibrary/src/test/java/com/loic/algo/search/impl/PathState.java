package com.loic.algo.search.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.loic.algo.search.core.HeuristicStrategy;
import com.loic.algo.search.core.TransitionStrategy;

public class PathState {
    private final List<Integer> path;

    public PathState(Integer ... path) {
        this.path = Arrays.asList(path);
    }

    public PathState(List<Integer> path) {
        this.path = new ArrayList<>(path);
    }

    public List<Integer> getPath() {
        return path;
    }
}
