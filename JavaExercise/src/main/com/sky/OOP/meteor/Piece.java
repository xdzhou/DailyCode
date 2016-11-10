package com.sky.OOP.meteor;

/**
 * Created by xuli on 10/11/16.
 */
public class Piece {
    public static final int CELL_COUNT = 5;
    public static final int PERMUTATION_COUNT = 12;

    private final Cell[] mCells;
    private int mCurrentMutation = 0;
    private final int mNum;

    public Piece(Cell[] cells, int num) {
        mCells = cells;
        mNum = num;
    }

    public int getNumber() {
        return mNum;
    }

    public Piece nextMutation() {
        mCurrentMutation ++;
        mCurrentMutation %= PERMUTATION_COUNT;
        if (mCurrentMutation == 0 || mCurrentMutation == 6) {
            rotatePiece();
            flipPiece();
        } else {
            rotatePiece();
        }
        return this;
    }

    public Cell getCell(int index) {
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

    public void unProcessing() {
        for (Cell cell : mCells) {
            cell.setProcessing(false);
        }
    }
}
