package com.loic.algo.search;

import java.util.List;

public abstract class Node<T> implements Cloneable {
    protected T mTransition;

    public abstract float heuristic();

    public final Node<T> getChildForTransition(T transition) {
        Node<T> child = clone();
        child.applyTransition(transition);
        return child;
    }

    protected void applyTransition(T transition) {
        mTransition = transition;
    }

    public abstract boolean isOver();

    public abstract List<T> getPossibleTransitions();

    public T getTransition() {
        return mTransition;
    }

    @Override
    protected Node<T> clone() {
        try {
            Node<T> node = (Node<T>) super.clone();
            node.mTransition = null;
            return node;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
