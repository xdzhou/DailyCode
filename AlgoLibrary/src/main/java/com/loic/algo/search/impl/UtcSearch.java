package com.loic.algo.search.impl;

import java.util.Date;
import java.util.List;
import java.util.Random;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.loic.algo.search.core.PathFinder;
import com.loic.algo.search.core.SearchPath;
import com.loic.algo.search.core.State;
import com.loic.algo.search.core.StateNode;
import com.loic.algo.search.core.Transition;

/*
 * UCT（Upper Confidence Bound for Trees） algorithm – the most popular algorithm in the MCTS family
 */
public class UtcSearch implements PathFinder {
    private static final float C = (float) Math.sqrt(2);

    private final Random mRandom = new Random(new Date().getTime());
    private final int simulateCount;

    private int maxDeep;

    public UtcSearch(int simulateCount) {
        Preconditions.checkState(simulateCount > 0, "simulate count have be more than 0");
        this.simulateCount = simulateCount;
    }

    @Override
    public <Trans extends Transition> SearchPath<Trans> find(State<Trans> root, int maxDeep) {
        Preconditions.checkState(maxDeep > 0, "maxDeep have be more than 0");

        StateNode<Info> rootNode = new StateNode<>(root, null, new Info());
        this.maxDeep = maxDeep;

        for (int i = 0; i < simulateCount; i++) {
            process(rootNode);
            if (Double.compare(rootNode.info().winCount, rootNode.info().simuCount) == 0) break;
        }

        List<Trans> list = Lists.newArrayList();
        StateNode<Info> curNode = rootNode;
        do {
            StateNode<Info> bestChild = getBestChild(curNode);
            list.add((Trans)bestChild.getAppliedTransition());
            curNode = bestChild;
        } while (!curNode.state().isTerminal() && curNode.children().size() == curNode.state().nextPossibleTransitions().size());

        return new SearchPath<>(list);
    }

    private void process(StateNode<Info> rootNode) {
        List<StateNode<Info>> path = selection(rootNode);
        expansionSimulation(path);
        double fitness = path.get(path.size() - 1).state().heuristic();
        backPropagation(fitness, path);
    }

    private void backPropagation(double winning, List<StateNode<Info>> path) {
        for (StateNode<Info> node : path) {
            node.info().appendWin(winning);
        }
    }

    private void expansionSimulation(List<StateNode<Info>> path) {
        StateNode<Info> leafNode = path.get(path.size() - 1);
        if (!leafNode.state().isTerminal() && path.size() - 1 < maxDeep) {
            List<Transition> transitions = leafNode.state().nextPossibleTransitions();
            int childIndex = mRandom.nextInt(transitions.size());
            Transition selectedTrans = transitions.get(childIndex);
            StateNode<Info> child = leafNode.getChild(selectedTrans);
            if (child == null) {
                child = new StateNode<>(leafNode.state().apply(selectedTrans), selectedTrans, new Info());
                leafNode.addChild(child);
            }
            path.add(child);
            expansionSimulation(path);
        }
    }

    private List<StateNode<Info>> selection(StateNode<Info> rootNode) {
        List<StateNode<Info>> path = Lists.newArrayList();
        StateNode<Info> curNode = rootNode;
        while (!curNode.state().isTerminal() && curNode.children().size() == curNode.state().nextPossibleTransitions().size()) {
            path.add(curNode);
            curNode = getBestChild(curNode);
        }
        path.add(curNode);
        return path;
    }

    private StateNode<Info> getBestChild(StateNode<Info> parent) {
        double best = Double.NEGATIVE_INFINITY;
        StateNode<Info> bestChild = null;
        for (StateNode<Info> child : parent.children()) {
            if (child.info().simuCount == 0) {
                throw new IllegalStateException("SimuCount could not be 0");
            } else {
                double value = (child.info().winCount / (double) child.info().simuCount + C * Math.sqrt(Math.log(parent.info().simuCount) / (double) child.info().simuCount));
                if (value > best || (Double.compare(value, best) == 0 && mRandom.nextBoolean())) {
                    best = value;
                    bestChild = child;
                }
            }
        }
        return bestChild;
    }


    private static final class Info {
        double winCount = 0f;
        int simuCount = 0;

        public void appendWin(double winning) {
            simuCount++;
            winCount += winning;
        }
    }
}
