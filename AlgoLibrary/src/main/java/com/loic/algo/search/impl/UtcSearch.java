package com.loic.algo.search.impl;

import com.google.common.base.Preconditions;
import com.loic.algo.search.TimeoutException;
import com.loic.algo.search.Timer;
import com.loic.algo.search.TreeSearchUtils;
import com.loic.algo.search.core.SearchParam;
import com.loic.algo.search.core.TreeSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static java.util.Objects.requireNonNull;

/*
 * UCT（Upper Confidence Bound for Trees） algorithm – the most popular algorithm in the MCTS family
 * 0 <= heuristic <= 1
 */
public class UtcSearch implements TreeSearch {
  private static final Logger LOG = LoggerFactory.getLogger(UtcSearch.class);
  private static final double C = Math.sqrt(2);

  private final Random mRandom = new Random(new Date().getTime());
  private final int simulateCount;

  public UtcSearch(int simulateCount) {
    Preconditions.checkState(simulateCount > 0, "simulate count have be more than 0");
    this.simulateCount = simulateCount;
  }

  @Override
  public <State, Trans> Optional<Trans> find(State root, SearchParam<State, Trans> param) {
    requireNonNull(root, "Root state is mandatory");
    requireNonNull(param, "SearchParam is mandatory");

    Optional<Trans> next = TreeSearchUtils.nextTrans(root, param.transitionStrategy());
    if (next != null) {
      return next;
    }

    StateNode<State, Trans, Info> rootNode = new StateNode<>(root, null, null, new Info());
    Timer timer = new Timer();
    timer.startTimer(param.timerDuration());
    for (int i = 0; i < simulateCount; i++) {
      try {
        timer.checkTime();
      } catch (TimeoutException e) {
        break;
      }
      process(param, rootNode);
    }

    StateNode<State, Trans, Info> bestChild = getBestChild(rootNode);
    return bestChild == null ? Optional.empty() : Optional.of(bestChild.getAppliedTransition());
  }

  private <State, Trans> void process(SearchParam<State, Trans> param, StateNode<State, Trans, Info> rootNode) {
    List<StateNode<State, Trans, Info>> path = selection(param, rootNode);
    expansionSimulation(param, path);
    int depth = path.size() - 1;
    double fitness = param.heuristicStrategy().heuristic(path.get(depth).state(), depth);
    Preconditions.checkState(fitness >= 0 && fitness <= 1, "heuristic function need return fitness 0 -> 1");
    //backPropagation
    path.forEach(n -> n.info().appendWin(fitness));
  }

  private <State, Trans> void expansionSimulation(SearchParam<State, Trans> param, List<StateNode<State, Trans, Info>> path) {
    StateNode<State, Trans, Info> leafNode = path.get(path.size() - 1);
    List<Trans> transitions = TreeSearchUtils.asStringSort(param.transitionStrategy().generate(leafNode.state()));
    if (!transitions.isEmpty() && path.size() - 1 < param.getMaxDepth()) {
      int childIndex = mRandom.nextInt(transitions.size());
      Trans selectedTrans = transitions.get(childIndex);
      StateNode<State, Trans, Info> child = leafNode.getChild(selectedTrans);
      if (child == null) {
        child = new StateNode<>(param.applyStrategy().apply(leafNode.state(), selectedTrans), leafNode, selectedTrans, new Info());
        leafNode.addChild(child);
      }
      path.add(child);
      expansionSimulation(param, path);
    }
  }

  private <State, Trans> List<StateNode<State, Trans, Info>> selection(SearchParam<State, Trans> param, StateNode<State, Trans, Info> rootNode) {
    List<StateNode<State, Trans, Info>> path = new ArrayList<>();
    StateNode<State, Trans, Info> curNode = rootNode;
    Set<Trans> transList;
    while (curNode.children().size() == (transList = param.transitionStrategy().generate(curNode.state())).size() && !transList.isEmpty()) {
      path.add(curNode);
      curNode = getBestChild(curNode);
    }
    path.add(curNode);
    return path;
  }

  private <State, Trans> StateNode<State, Trans, Info> getBestChild(StateNode<State, Trans, Info> parent) {
    if (parent.children().size() == 1) {
      return parent.children().get(0);
    }
    double best = Double.NEGATIVE_INFINITY;
    StateNode<State, Trans, Info> bestChild = null;
    Info[] childInfo = new Info[parent.children().size()];
    int index = 0;
    for (StateNode<State, Trans, Info> child : parent.children()) {
      Preconditions.checkArgument(child.info().simuCount > 0, "SimuCount could not be 0");
      childInfo[index++] = child.info();
      double value = (child.info().winCount / (double) child.info().simuCount + C * Math.sqrt(Math.log(parent.info().simuCount) / (double) child.info().simuCount));
      if (value > best || (Double.compare(value, best) == 0 && mRandom.nextBoolean())) {
        best = value;
        bestChild = child;
      }
    }
    LOG.trace("best child: {}, all child info: {}", bestChild.info(), childInfo);
    return bestChild;
  }


  private static final class Info {
    private double winCount = 0f;
    private int simuCount = 0;

    public void appendWin(double winning) {
      simuCount++;
      winCount += winning;
    }

    @Override
    public String toString() {
      return "" + winCount + '/' + simuCount;
    }
  }
}
