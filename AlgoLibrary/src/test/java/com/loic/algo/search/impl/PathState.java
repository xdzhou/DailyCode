package com.loic.algo.search.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PathState {
    private final List<Integer> path;

    public PathState(Integer... path) {
        this.path = Arrays.asList(path);
    }

    public PathState(List<Integer> path) {
        this.path = new ArrayList<>(path);
    }

    public List<Integer> getPath() {
        return path;
    }
}
