package com.loic.algo.search.impl;

public interface MapInfo {
    int getEndPos();

    int getWidth();

    boolean reachable(int posX, int posY);

    double heuristicToEnd(int lengthPassed, int posX, int posY);
}
