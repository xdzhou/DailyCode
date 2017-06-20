package com.loic.algo.search.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

class StateNode<State, Trans, T> {
    private final State state;
    private final Trans appliedTransition;
    private int depth;
    private T additionalInfo;

    private StateNode<State, Trans, T> parent;
    private List<StateNode<State, Trans, T>> children;

    public StateNode(State state, StateNode<State, Trans, T> parent, Trans appliedTransition) {
        this(state, parent, appliedTransition, null);
    }

    public StateNode(State state, StateNode<State, Trans, T> parent, Trans appliedTransition, T additionalInfo) {
        this.state = Objects.requireNonNull(state);
        this.parent = parent;
        this.appliedTransition = appliedTransition;
        this.additionalInfo = additionalInfo;
        depth = parent == null ? 0 : parent.depth + 1;
    }

    public State state() {
        return state;
    }

    public StateNode<State, Trans, T> parent() {
        return parent;
    }

    public T info() {
        return additionalInfo;
    }

    public void setInfo(T info) {
        additionalInfo = info;
    }

    public int depth() {
        return depth;
    }

    public List<StateNode<State, Trans, T>> children() {
        return children == null ? Collections.emptyList() : children;
    }

    public void addChild(StateNode<State, Trans, T> child) {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(child);
    }

    public StateNode<State, Trans, T> getChild(Trans appliedTransition) {
        Objects.requireNonNull(appliedTransition);
        for (StateNode<State, Trans, T> child : children()) {
            if (appliedTransition.equals(child.appliedTransition)) {
                return child;
            }
        }
        return null;
    }

    public Trans getAppliedTransition() {
        return appliedTransition;
    }

    public List<Trans> getPath() {
        List<Trans> list = new LinkedList<>();
        StateNode<State, Trans, T> curNode = this;
        while (curNode != null) {
            list.add(0, curNode.appliedTransition);
            curNode = curNode.parent;
        }
        return list;
    }
}
