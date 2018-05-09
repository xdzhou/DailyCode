package com.loic.algo.search.impl;

public interface MapInfo {
  void setStartPosition(int pos);

  int getEndPos();

  int getWidth();

  boolean reachable(int posX, int posY);

  double heuristicToEnd(int lengthPassed, int posX, int posY);
}
