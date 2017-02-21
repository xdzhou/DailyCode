package com.sky.OOP.meteor;

public class BoardCell extends Cell {
    private Piece mPiece;

    public Piece getPiece() {
        return mPiece;
    }

    void setPiece(Piece piece) {
        mPiece = piece;
    }

    int getIslandCount() {
        if (mPiece != null || isProcessing()) {
            return 0;
        } else {
            setProcessing(true);
            int num = 1;
            for (int i = 0; i < NEIGHBOUR_COUNT; i++) {
                BoardCell cell = (BoardCell) getNeighbour(i);
                if (cell != null) num += cell.getIslandCount();
            }
            return num;
        }
    }
}
