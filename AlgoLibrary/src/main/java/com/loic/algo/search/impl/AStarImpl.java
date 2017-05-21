package com.loic.algo.search.impl;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.loic.algo.search.core.State;
import com.loic.algo.search.core.Transition;
import com.loic.algo.search.core.TreeSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AStarImpl implements TreeSearch {
    private static final Logger LOG = LoggerFactory.getLogger(AStarImpl.class);

    @Override
    public <Trans extends Transition> List<Trans> find(State<Trans> root, int maxDeep) {
        Objects.requireNonNull(root, "Root state is mandatory");
        Preconditions.checkState(maxDeep > 0, "Max deep must bigger than 0");

        PriorityQueue<StateAndTrans<Trans>> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(o -> -o.fitness));
        priorityQueue.add(new StateAndTrans<>(root, null));

        StateAndTrans<Trans> bestState = null;
        while (!priorityQueue.isEmpty()) {
            StateAndTrans<Trans> stateAndTrans = priorityQueue.poll();
            LOG.debug("poll state for transitions {}, with fitness {}", Arrays.toString(stateAndTrans.trans), stateAndTrans.fitness);
            int transLen = stateAndTrans.trans == null ? 0 : stateAndTrans.trans.length;
            if (stateAndTrans.state.isTerminal() || transLen >= maxDeep) {
                bestState = stateAndTrans;
                break;
            }
            stateAndTrans.state.nextPossibleTransitions().stream()
                    .map(t -> {
                        State<Trans> newState = stateAndTrans.state.apply(t);
                        int len = stateAndTrans.trans == null ? 1 : stateAndTrans.trans.length + 1;
                        Trans[] newTrans = (Trans[]) Array.newInstance(t.getClass(), len);
                        if (len > 1) {
                            System.arraycopy(stateAndTrans.trans, 0, newTrans, 0, len - 1);
                        }
                        newTrans[len - 1] = t;

                        return new StateAndTrans<>(newState, newTrans);
                    }).forEach(item -> {
                        priorityQueue.add(item);
                        LOG.debug("add {} to queue, with fitness {}", Arrays.toString(item.trans), item.fitness);
                    });
        }

        return ImmutableList.copyOf(bestState.trans);
    }

    private static final class StateAndTrans<Trans extends Transition> {
        private State<Trans> state;
        private Trans[] trans;
        private double fitness;

        public StateAndTrans(State<Trans> state, Trans[] trans) {
            this.state = state;
            this.trans = trans;
            fitness = state.heuristic();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            StateAndTrans<?> that = (StateAndTrans<?>) o;

            // Probably incorrect - comparing Object[] arrays with Arrays.equals
            return Arrays.equals(trans, that.trans);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(trans);
        }
    }
}
