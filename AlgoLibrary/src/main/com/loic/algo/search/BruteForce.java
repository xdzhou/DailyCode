package com.loic.algo.search;

public class BruteForce<N extends Node<T>, T> {

    public void getNextTransition(N root, int depth) {
        process(root, depth);
    }

    private float process(N root, int depth) {
        if (depth == 0 || root.isOver()) {
            return root.heuristic();
        } else {
            float best = Float.NEGATIVE_INFINITY;
            N bestChild = null;
            for(T transition : root.getPossibleTransitions()) {
                N child = (N) root.applyTransition(transition);
                if (child != null) {
                    child.setTransition(transition);
                    onChildNodeCreated(root, child, transition);
                    float value = process(child, depth - 1);
                    if (value > best) {
                        best = value;
                        bestChild = child;
                        if (best == Float.POSITIVE_INFINITY) break;
                    }
                }
            }
            root.setBestChild(bestChild);
            return best;
        }
    }

    protected void onChildNodeCreated(N parent, N child, T transition) {

    }
}
