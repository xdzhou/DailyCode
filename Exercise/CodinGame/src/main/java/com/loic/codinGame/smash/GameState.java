package com.loic.codinGame.smash;

import java.util.Arrays;

class GameState implements Cloneable {
  private BordState myBord;
  private int myScore;
  private double myBlockCount;

  private BordState otherBord;
  private int otherScore;
  private double otherBlockCount;

  private boolean myTurn = true;
  private int dropCount = 0;

  public GameState() {
    myBord = new BordState();
    otherBord = new BordState();
  }

  public void apply(Drop drop, ColorSet[] colorSet) {
    int dropIndex = dropCount / 2;
    if (myTurn) {
      int score = myBord.drop(drop.column(), drop.rotation(), colorSet[dropIndex]);
      myScore += score;
      otherBlockCount = score / 70d;
      if (otherBlockCount >= 6) {
        int lineCount = (int) (otherBlockCount / 6);
        otherBord.dropBlockLine(lineCount);
        otherBlockCount -= (lineCount * 6);
      }
    } else {
      int score = otherBord.drop(drop.column(), drop.rotation(), colorSet[dropIndex]);
      otherScore += score;
      myBlockCount = score / 70d;
      if (myBlockCount >= 6) {
        int lineCount = (int) (myBlockCount / 6);
        myBord.dropBlockLine(lineCount);
        myBlockCount -= (lineCount * 6);
      }
    }
    myTurn = !myTurn;
    dropCount++;
  }

  public void asRoot() {
    myTurn = true;
    dropCount = 0;
  }

  public boolean isMyTurn() {
    return myTurn;
  }

  public int otherScore() {
    return otherScore;
  }

  public int myScore() {
    return myScore;
  }

  public BordState myBord() {
    return myBord;
  }

  public BordState otherBord() {
    return otherBord;
  }

  public void setMyScore(int score) {
    myScore = score;
  }

  public void setOtherScore(int score) {
    otherScore = score;
  }

  public boolean isOver() {
    return myBord.isOver() || otherBord.isOver();
  }

  public int extraScore() {
    return myBord.extraScore() - otherBord.extraScore();
  }

  @Override
  protected GameState clone() {
    try {
      GameState state = (GameState) super.clone();
      state.myBord = myBord.clone();
      state.otherBord = otherBord.clone();
      return state;
    } catch (CloneNotSupportedException e) {
      throw new IllegalStateException(e);
    }
  }

  @SuppressWarnings("PMD")
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("GameState : \n");
    sb.append("Score: ").append(myScore).append("          ").append(otherScore).append('\n');
    sb.append("Block: ").append(myBlockCount).append("          ").append(otherBlockCount).append('\n');
    for (int line = 0; line < BordState.HEIGHT; line++) {
      sb.append(Arrays.toString(myBord.getData(line)))
        .append("     ")
        .append(Arrays.toString(otherBord.getData(line)))
        .append('\n');
    }
    return sb.toString();
  }
}
