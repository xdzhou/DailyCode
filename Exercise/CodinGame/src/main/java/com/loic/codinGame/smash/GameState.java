package com.loic.codinGame.smash;

import java.util.Arrays;

import com.google.common.base.Preconditions;

class GameState implements Cloneable {
    private BordState myBord;
    private int myScore;
    private int myBlockCount;

    private BordState otherBord;
    private int otherScore;
    private int otherBlockCount;

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
            //TODO
        } else {
            int score = otherBord.drop(drop.column(), drop.rotation(), colorSet[dropIndex]);
            otherScore += score;
        }
        myTurn = !myTurn;
        dropCount ++;
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

    public void checkMyScore(int score) {
        Preconditions.checkState(myScore == score);
    }

    public void checkOtherScore(int score) {
        Preconditions.checkState(otherScore == score);
    }

    public boolean isOver() {
        return myBord.isOver() || otherBord.isOver();
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
