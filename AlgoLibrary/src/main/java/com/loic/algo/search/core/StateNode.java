package com.loic.algo.search.core;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.google.common.collect.Lists;

public class StateNode<T> {
    private final State state;
    private final Transition appliedTransition;

    private final T additionalInfo;

    private List<StateNode<T>> children;


    public StateNode(State state, Transition appliedTransition, T additionalInfo) {
        this.state = Objects.requireNonNull(state);
        this.appliedTransition = appliedTransition;
        this.additionalInfo = additionalInfo;
    }

    public State state() {
        return state;
    }

    public T info() {
        return additionalInfo;
    }

    public List<StateNode<T>> children() {
        return children == null ? Collections.emptyList() : children;
    }

    public void addChild(StateNode<T> child) {
        if (children == null) {
            children = Lists.newArrayList();
        }
        children.add(child);
    }

    public StateNode<T> getChild(Transition appliedTransition) {
        Objects.requireNonNull(appliedTransition);
        for(StateNode<T> child : children()) {
            if(appliedTransition.equals(child.appliedTransition)) {
                return child;
            }
        }
        return null;
    }

    public Transition getAppliedTransition() {
        return appliedTransition;
    }
}
