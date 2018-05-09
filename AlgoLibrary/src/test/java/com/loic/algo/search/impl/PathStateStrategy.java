package com.loic.algo.search.impl;

import com.google.common.collect.ImmutableSet;
import com.loic.algo.search.core.ApplyStrategy;
import com.loic.algo.search.core.HeuristicStrategy;
import com.loic.algo.search.core.TransitionStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class PathStateStrategy implements ApplyStrategy<PathState, Direction>, HeuristicStrategy<PathState>, TransitionStrategy<PathState, Direction> {
  private final MapInfo mapInfo;
  private int delta;

  public PathStateStrategy(MapInfo mapInfo) {
    this.mapInfo = mapInfo;
  }

  public void newRoot(PathState state) {
    List<Integer> path = state.getPath();
    delta = path.size();
    mapInfo.setStartPosition(path.get(path.size() - 1));
  }

  @Override
  public PathState apply(PathState pathState, Direction direction) {
    List<Integer> path = pathState.getPath();
    int pos = path.get(path.size() - 1);
    int line = pos / mapInfo.getWidth();
    int col = pos % mapInfo.getWidth();
    switch (direction) {
      case UP:
        line--;
        break;
      case DOWN:
        line++;
        break;
      case LEFT:
        col--;
        break;
      case RIGHT:
        col++;
        break;
      default:
        throw new UnsupportedOperationException("Unknown direction : " + direction);
    }
    int nextPos = line * mapInfo.getWidth() + col;
    List<Integer> list = new ArrayList<>(path);
    list.add(nextPos);
    return new PathState(list);
  }

  @Override
  public double heuristic(PathState pathState, int depth) {
    List<Integer> path = pathState.getPath();
    //Assert.assertEquals(path.size(), depth + delta);

    int pos = path.get(path.size() - 1);
    int line = pos / mapInfo.getWidth();
    int col = pos % mapInfo.getWidth();
    double fitness = mapInfo.heuristicToEnd(path.size(), line, col);
    if (fitness != 1 && generate(pathState).isEmpty()) {
      return 0;
    }
    return fitness * Math.pow(0.8, depth);
  }

  @Override
  public Set<Direction> generate(PathState pathState) {
    List<Integer> path = pathState.getPath();
    if (mapInfo.getEndPos() == path.get(path.size() - 1)) {
      return Collections.emptySet();
    }
    int pos = path.get(path.size() - 1);
    int line = pos / mapInfo.getWidth();
    int col = pos % mapInfo.getWidth();
    List<Direction> directions = new ArrayList<>(Direction.values().length);
    if (reachable(path, line - 1, col)) {
      directions.add(Direction.UP);
    }
    if (reachable(path, line + 1, col)) {
      directions.add(Direction.DOWN);
    }
    if (reachable(path, line, col - 1)) {
      directions.add(Direction.LEFT);
    }
    if (reachable(path, line, col + 1)) {
      directions.add(Direction.RIGHT);
    }
    return ImmutableSet.copyOf(directions);
  }

  private boolean reachable(List<Integer> path, int posX, int posY) {
    if (path.indexOf(posX * mapInfo.getWidth() + posY) < 0) {
      return mapInfo.reachable(posX, posY);
    }
    return false;
  }
}
