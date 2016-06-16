package com.loic.algo.search;

import java.util.List;

public abstract class AbstractNode<T extends Transition> {
    protected T mTransition;
    protected AbstractNode<T> mBestChild;

    public abstract float heuristic();

    public abstract AbstractNode<T> applyTransition(T transition);

    public abstract boolean isOver();

    public abstract List<T> getPossibleTransitions();

    public AbstractNode<T> getBestChild() {
        return mBestChild;
    }

    public void setBestChild(AbstractNode<T> bestChild) {
        mBestChild = bestChild;
    }

    public T getTransition() {
        return mTransition;
    }

    public void setTransition(T transition) {
        mTransition = transition;
    }
}
