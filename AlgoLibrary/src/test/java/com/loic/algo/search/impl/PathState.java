package com.loic.algo.search.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.loic.algo.search.core.State;

public class PathState implements State<Direction> {
    private final MapInfo mapInfo;
    private final List<Integer> path;

    public PathState(MapInfo mapInfo, Integer ... path) {
        this.mapInfo = mapInfo;
        this.path = Arrays.asList(path);
    }

    public PathState(MapInfo mapInfo, List<Integer> path) {
        this.mapInfo = mapInfo;
        this.path = new ArrayList<>(path);
    }

    public void asRoot() {
        mapInfo.setStartPosition(path.get(path.size() - 1));
    }

    @Override
    public boolean isTerminal() {
        return mapInfo.getEndPos() == path.get(path.size() - 1) ||
                nextPossibleTransitions().isEmpty();
    }

    @Override
    public double heuristic() {
        if (nextPossibleTransitions().isEmpty()) return 0;
        int pos = path.get(path.size() - 1);
        int line = pos / mapInfo.getWidth();
        int col = pos % mapInfo.getWidth();
        return mapInfo.heuristicToEnd(path.size(), line, col);
    }

    @Override
    public PathState apply(Direction transition) {
        int pos = path.get(path.size() - 1);
        int line = pos / mapInfo.getWidth();
        int col = pos % mapInfo.getWidth();
        switch (transition) {
            case UP:
                line --;
                break;
            case DOWN:
                line ++;
                break;
            case LEFT:
                col --;
                break;
            case RIGHT:
                col ++;
                break;
            default:
                throw new UnsupportedOperationException("Unknown direction : " + transition);
        }
        int nextPos = line * mapInfo.getWidth() + col;
        List<Integer> list = new ArrayList<>(path);
        list.add(nextPos);
        return new PathState(mapInfo, list);
    }

    @Override
    public List<Direction> nextPossibleTransitions() {
        int pos = path.get(path.size() - 1);
        int line = pos / mapInfo.getWidth();
        int col = pos % mapInfo.getWidth();
        List<Direction> directions = new ArrayList<>(Direction.values().length);
        if (reachable(line - 1, col)) directions.add(Direction.UP);
        if (reachable(line + 1, col)) directions.add(Direction.DOWN);
        if (reachable(line, col - 1)) directions.add(Direction.LEFT);
        if (reachable(line, col + 1)) directions.add(Direction.RIGHT);
        return ImmutableList.copyOf(directions);
    }

    private boolean reachable(int posX, int posY) {
        if (path.indexOf(posX * mapInfo.getWidth() + posY) < 0) {
            return mapInfo.reachable(posX, posY);
        }
        return false;
    }
}
