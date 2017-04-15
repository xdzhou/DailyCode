package com.loic.algo.search;

public class BruteForce<N extends BruteForce.BruteForceNode<T>, T> {

    public void execute(N root, int depth) {
        process(root, depth);
    }

    private float process(N root, int depth) {
        if (depth == 0 || root.isOver()) {
            return root.heuristic();
        } else {
            float best = Float.NEGATIVE_INFINITY;
            N bestChild = null;
            for(T transition : root.getPossibleTransitions()) {
                N child = (N) root.getChildForTransition(transition);
                if (child != null) {
                    float value = process(child, depth - 1);
                    if (value > best) {
                        best = value;
                        bestChild = child;
                        if (best == Float.POSITIVE_INFINITY) break;
                    }
                }
            }
            root.mBestChild = bestChild;
            return best;
        }
    }

    public static abstract class BruteForceNode<T> extends Node<T> {
        protected Node<T> mBestChild;

        public Node<T> getBestChild() {
            return mBestChild;
        }

        @Override
        protected BruteForceNode<T> clone() {
            BruteForceNode<T> node = (BruteForceNode<T>) super.clone();
            node.mBestChild = null;
            return node;
        }
    }
}
