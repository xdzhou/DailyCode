package com.loic.OOP.meteor;

public class Piece {
    static final int CELL_COUNT = 5;
    static final int PERMUTATION_COUNT = 12;

    private final Cell[] mCells;
    private final int mNum;
    private int mCurrentMutation = 0;

    Piece(Cell[] cells, int num) {
        mCells = cells;
        mNum = num;
    }

    public int getNumber() {
        return mNum;
    }

    Piece nextMutation() {
        mCurrentMutation++;
        mCurrentMutation %= PERMUTATION_COUNT;
        if (mCurrentMutation == 0 || mCurrentMutation == 6) {
            rotatePiece();
            flipPiece();
        } else {
            rotatePiece();
        }
        return this;
    }

    Cell getCell(int index) {
        return mCells[index];
    }

    private void rotatePiece() {
        for (Cell cell : mCells) {
            cell.rotate();
        }
    }

    private void flipPiece() {
        for (Cell cell : mCells) {
            cell.flip();
        }
    }

    void unProcessing() {
        for (Cell cell : mCells) {
            cell.setProcessing(false);
        }
    }
}
