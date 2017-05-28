package com.loic.algo.search.impl;

import static java.util.Objects.requireNonNull;

import java.util.Optional;
import java.util.PriorityQueue;

import com.loic.algo.search.TimeoutException;
import com.loic.algo.search.Timer;
import com.loic.algo.search.core.SearchParam;
import com.loic.algo.search.core.TreeSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AStarImpl implements TreeSearch {
    private final Timer timer = new Timer();
    private static final Logger LOG = LoggerFactory.getLogger(AStarImpl.class);

    @Override
    public <Trans, State> Optional<Trans> find(State root, SearchParam<Trans, State> param) {
        requireNonNull(root, "Root state is mandatory");
        requireNonNull(param, "SearchParam is mandatory");

        StateNode<Trans, State, Double> rootNode = new StateNode<>(root, null, null);
        PriorityQueue<StateNode<Trans, State, Double>> priorityQueue = new PriorityQueue<>((o1, o2) -> Double.compare(o2.info(), o1.info()));
        priorityQueue.add(rootNode);

        timer.startTimer(param.timerDuration());
        while (!priorityQueue.isEmpty()) {
            try {
                timer.checkTime();
            } catch (TimeoutException e) {
                LOG.debug("Timeout, quit loop");
                break;
            }
            StateNode<Trans, State, Double> curNode = priorityQueue.poll();
            LOG.trace("poll state for transitions {}, with fitness {}", curNode.getPath(), curNode.info());

            double bestChild = param.transitionStrategy().generate(curNode.state()).stream()
                .map(t -> {
                    State childState = param.applyStrategy().apply(curNode.state(), t);
                    return new StateNode<>(childState, curNode, t, param.heuristicStrategy().heuristic(childState, curNode.depth() + 1));
                })
                .peek(curNode::addChild)
                .peek(priorityQueue::add)
                .reduce(Double.NEGATIVE_INFINITY, (best, node) -> Math.max(best, node.info()), Math::max);

            updateParent(curNode, bestChild);
            curNode.setInfo(bestChild);
        }

        return rootNode.children().stream()
            .reduce((node1, node2) -> node1.info() > node2.info() ? node1 : node2)
            .map(StateNode::getAppliedTransition);
    }

    private <Trans, State> void updateParent(StateNode<Trans, State, Double> node, double best) {
        if (node.info() != null && Double.compare(node.info(), best) < 0) {
            node.setInfo(best);
            updateParent(node.parent(), best);
        }
    }

}
