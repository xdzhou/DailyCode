package com.loic.codinGame.smash;

import java.util.ArrayList;
import java.util.List;

class Zone {
  private char color;
  private int blockCount = 0;
  private List<Integer> indexs;

  Zone(char color) {
    this.color = color;
  }

  public int size() {
    return (indexs.size() - blockCount >= BordState.MIN_LIEN) ? indexs.size() : indexs.size() - blockCount;
  }

  public char getColor() {
    return color;
  }

  public int getBlockCount() {
    return blockCount;
  }

  public List<Integer> getIndexs() {
    return indexs;
  }

  public void addIndex(int index) {
    if (indexs == null) {
      indexs = new ArrayList<>();
    }
    indexs.add(index);
  }

  public void addBlock(int index) {
    if (indexs == null) {
      indexs = new ArrayList<>();
    }
    indexs.add(index);
    blockCount++;
  }

  public boolean containIndex(int index) {
    return indexs != null && indexs.contains(index);
  }

  public int heuristic() {
    int groupSize = indexs.size() - blockCount;
    switch (groupSize) {
      case 1:
        return 0;
      case 2:
        return 2;
      case 3:
        return 3 + blockCount;
      default:
        throw new IllegalStateException("Why we got group count : " + groupSize);
    }
  }
}
