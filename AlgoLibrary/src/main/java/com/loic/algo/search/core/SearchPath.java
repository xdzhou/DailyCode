package com.loic.algo.search.core;

import java.util.List;

import com.google.common.collect.ImmutableList;

public class SearchPath<Trans extends Transition> {
    private final ImmutableList<Trans> path;

    public SearchPath(Trans... transitions) {
        path = ImmutableList.copyOf(transitions);
    }

    public SearchPath(List<Trans> transitions) {
        path = ImmutableList.copyOf(transitions);
    }

    public int deep() {
        return path.size();
    }

    public List<Trans> getTransitions() {
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
