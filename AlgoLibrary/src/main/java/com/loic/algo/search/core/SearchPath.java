package com.loic.algo.search.core;

import java.util.List;

import com.google.common.collect.ImmutableList;

public class SearchPath {
    private final ImmutableList<Transition> path;

    public SearchPath(Transition... transitions) {
        path = ImmutableList.copyOf(transitions);
    }

    public SearchPath(List<Transition> transitions) {
        path = ImmutableList.copyOf(transitions);
    }

    public int deep() {
        return path.size();
    }

    List<Transition> getTransitions() {
        return path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchPath path1 = (SearchPath) o;

        return path.equals(path1.path);
    }

    @Override
    public int hashCode() {
        return path.hashCode();
    }
}
