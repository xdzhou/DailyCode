package com.loic.algo.search.impl;

import java.util.List;

import com.loic.algo.search.core.PathFinder;
import com.loic.algo.search.core.SearchPath;
import com.loic.algo.search.core.State;
import com.loic.algo.search.core.Transition;

/**
 * KeyWord : game theory, decision theory, DFS
 */
public class MinimaxAlphaBeta implements PathFinder {
    private State root;
    private Transition bestTrans;

    @Override
    public SearchPath find(State root, int maxDeep) {
        this.root = root;
        alphaBeta(root, maxDeep, Double.MIN_VALUE, Double.MAX_VALUE, true);

        return new SearchPath(bestTrans);
    }

    private double alphaBeta(State state, int deep, double alpha, double beta, boolean maxPlayer) {
        if(deep <= 0 || state.isTerminal()) {
            return state.heuristic();
        }
        if (maxPlayer) {
            double best = Double.MIN_VALUE;
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
