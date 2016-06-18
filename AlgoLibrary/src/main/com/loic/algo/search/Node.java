package com.loic.algo.search;

import java.util.List;

public abstract class Node<T> implements Cloneable {
    protected T mTransition;
    protected Node<T> mBestChild;

    public abstract float heuristic();

    public abstract Node<T> applyTransition(T transition);

    public abstract boolean isOver();

    public abstract List<T> getPossibleTransitions();

    public Node<T> getBestChild() {
        return mBestChild;
    }

    public void setBestChild(Node<T> bestChild) {
        mBestChild = bestChild;
    }

    public T getTransition() {
        return mTransition;
    }

    public void setTransition(T transition) {
        mTransition = transition;
    }

    @Override
    protected Node<T> clone() {
        try {
            Node<T> node = (Node<T>) super.clone();
            node.mBestChild = null;
            node.mTransition = null;
            return node;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
