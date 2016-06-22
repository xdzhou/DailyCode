package com.loic.algo.search;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MonteCarlo<N extends MonteCarlo.MonteCarloNode<T>, T> {
    private static final float C = (float) Math.sqrt(2);

    private int mMaxDepth;
    private N mRoot;
    private Random mRandom = new Random(new Date().getTime());

    public void execute(N root, int simulationCount, int maxDepth) {
        mRoot = root;
        mRoot.mChildren = null;
        mMaxDepth = maxDepth;
        for(int i = 0; i < simulationCount; i++) {
            process();
            if (mRoot.mWin == mRoot.mSimulationCount) break;
        }
    }

    private void process() {
        List<N> path = selection();
        N leafNode = path.get(path.size() - 1);
        N selectedNode = null;
        if (leafNode != mRoot && leafNode.mSimulationCount == 0 ) {
            selectedNode = leafNode;
            path.remove(path.size() - 1);
        } else {
            selectedNode = expansion(leafNode, path.size() - 1);
        }
        float winning;
        if (selectedNode == null) {
            winning = leafNode.heuristic();
        } else {
            winning = simulation(selectedNode, path.size());
        }
        //System.err.println("winning : "+winning);
        backPropagation(winning, path);
    }

    private List<N> selection() {
        List<N> path = new ArrayList<N>();
        N curNode = mRoot;
        while (curNode.getChildren() != null && ! curNode.getChildren().isEmpty()) {
            path.add(curNode);
            curNode = getBestChild(curNode);
        }
        path.add(curNode);
        return path;
    }

    @SuppressWarnings("unchecked")
    public N getBestChild(N parent) {
        double best = Double.NEGATIVE_INFINITY;
        MonteCarloNode bestChild = null;
        for(MonteCarloNode child : parent.getChildren()) {
            if (child.mSimulationCount == 0) {
                bestChild = child;
                break;
            } else {
                double value = (child.mWin / (float) child.mSimulationCount + C * Math.sqrt(Math.log(parent.mSimulationCount) / (float) child.mSimulationCount));
                if (value > best || (value == best && mRandom.nextBoolean())) {
                    best = value;
                    bestChild = child;
                }
            }
        }
        return (N) bestChild;
    }

    @SuppressWarnings("unchecked")
    private N expansion(N leafNode, int leafDepth) {
        if (leafNode.isOver() || leafDepth >= mMaxDepth) {
            return null;
        } else {
            List<T> transitions = leafNode.getPossibleTransitions();
            leafNode.mChildren = new ArrayList<MonteCarloNode<T>>(transitions.size());
            for(T t : transitions) {
                N child = (N) leafNode.getChildForTransition(t);
                if (child != null) {
                    leafNode.addChild(child);
                }
            }
            int childIndex = mRandom.nextInt(leafNode.mChildren.size());
            return (N) leafNode.mChildren.get(childIndex);
        }
    }

    @SuppressWarnings("unchecked")
    private float simulation(N node, int depth) {
        float winning;
        if (depth >= mMaxDepth) {
            winning = node.heuristic();
        } else {
            //System.err.println("simulation in depth "+depth+" for Node "+node.print(false));
            N curNode = node;
            for (int i = depth + 1; i <= mMaxDepth && !curNode.isOver(); i++) {
                List<T> transitions = curNode.getPossibleTransitions();
                int selectIndex = mRandom.nextInt(transitions.size());
                curNode = (N) curNode.getChildForTransition(transitions.get(selectIndex));
            }
            winning = curNode.heuristic();
        }
        node.applyWinning(winning);
        return winning;
    }

    private void backPropagation(float winning, List<N> path) {
        for(N n : path) {
            n.applyWinning(winning);
        }
    }

    public abstract static class MonteCarloNode<T> extends Node<T> {
        float mWin = 0f;
        int mSimulationCount = 0;
        List<MonteCarloNode<T>> mChildren;

        public void applyWinning(float winning) {
            mSimulationCount ++;
            mWin += winning;
        }

        public void addChild(MonteCarloNode child) {
            if (mChildren == null) mChildren = new ArrayList<MonteCarloNode<T>>();
            mChildren.add(child);
        }

        public List<MonteCarloNode<T>> getChildren() {
            return mChildren;
        }

        @Override
        protected MonteCarloNode<T> clone() {
            MonteCarloNode<T> node = (MonteCarloNode<T>) super.clone();
            node.mWin = 0f;
            node.mSimulationCount = 0;
            node.mChildren = null;
            return node;
        }

        @Override
        public String toString() {
            return "Win=" + mWin +
                    ", SimulationCount=" + mSimulationCount +
                    ", Children=" + mChildren;
        }
    }
}
