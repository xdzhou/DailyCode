package com.loic.algo.search.impl;

import java.util.List;
import java.util.Objects;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.loic.algo.search.core.State;
import com.loic.algo.search.core.Transition;
import com.loic.algo.search.core.TreeSearch;

/**
 * KeyWord : game theory, decision theory, DFS
 */
public class MinimaxAlphaBeta implements TreeSearch {
    private State root;
    private Transition bestTrans;

    @Override
    public <Trans extends Transition> List<Trans> find(State<Trans> root, int maxDeep) {
        Objects.requireNonNull(root, "Root state is mandatory");
        Preconditions.checkState(maxDeep > 0, "Max deep must bigger than 0");

        this.root = root;
        alphaBeta(root, maxDeep, Double.MIN_VALUE, Double.MAX_VALUE, true);

        List<Trans> path = ImmutableList.of((Trans) bestTrans);
        root = null;
        bestTrans = null;
        return path;
    }

    private double alphaBeta(State state, int deep, double alpha, double beta, boolean maxPlayer) {
        if(deep <= 0 || state.isTerminal()) {
            return state.heuristic();
        }
        if (maxPlayer) {
            double best = Double.NEGATIVE_INFINITY;
            List<Transition> transitions = state.nextPossibleTransitions();
            for(Transition trans : transitions) {
                double childValue = alphaBeta(state.apply(trans), deep - 1, alpha, beta, false);
                if (childValue > best) {
                    best = childValue;
                    if (root == state) {
                        bestTrans = trans;
                    }
                }
                alpha = Math.max(alpha, best);
                if(best <= alpha) break;
            }
            return best;
        } else {
            double best = Double.MAX_VALUE;
            List<Transition> transitions = state.nextPossibleTransitions();
            for(Transition trans : transitions) {
                best = Math.min(best, alphaBeta(state.apply(trans), deep - 1, alpha, beta, true));
                beta = Math.min(beta, best);
                if(best <= alpha) break;
            }
            return best;
        }
    }
}
