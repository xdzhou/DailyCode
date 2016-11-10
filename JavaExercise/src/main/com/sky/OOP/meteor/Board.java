package com.sky.OOP.meteor;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public static final int CELL_COUNT = 50;
    private static final int CELL_COUNT_IN_ROW = 5;

    private final BoardCell[] boardCells = new BoardCell[CELL_COUNT];

    public Board() {
        for(int i = 0; i < CELL_COUNT; i++) {
            boardCells[i] = new BoardCell();
        }
        for(int i = 0; i < CELL_COUNT; i++) {
            initCellSide(boardCells[i], i);
        }
    }

    private void initCellSide(BoardCell boardCell, int index) {
        int row = index / CELL_COUNT_IN_ROW;
        // Check if cell is in last or first column
        boolean isFirst = (index % CELL_COUNT_IN_ROW == 0);
        boolean isLast = ((index + 1) % CELL_COUNT_IN_ROW == 0);
        if (row%2 == 0) { // Even rows
            if (row != 0) {
                // Northern neighbours
                if (!isFirst) {
                    boardCell.setNeighbour(Cell.NORTH_WEST, boardCells[index-6]);
                }
                boardCell.setNeighbour(Cell.NORTH_EAST, boardCells[index-5]);
            }
            if (row != ((CELL_COUNT / CELL_COUNT_IN_ROW)-1)) {
                // Southern neighbours
                if (!isFirst) {
                    boardCell.setNeighbour(Cell.SOUTH_WEST, boardCells[index+4]);
                }
                boardCell.setNeighbour(Cell.SOUTH_EAST, boardCells[index+5]);
            }
        }
        else { // Uneven rows
            // Northern neighbours
            if (!isLast) {
                boardCell.setNeighbour(Cell.NORTH_EAST, boardCells[index-4]);
            }
            boardCell.setNeighbour(Cell.NORTH_WEST, boardCells[index-5]);
            // Southern neighbours
            if (row != ((CELL_COUNT / CELL_COUNT_IN_ROW)-1)) {
                if (!isLast) {
                    boardCell.setNeighbour(Cell.SOUTH_EAST, boardCells[index+6]);
                }
                boardCell.setNeighbour(Cell.SOUTH_WEST, boardCells[index+5]);
            }
        }
        // Set the east and west neighbours
        if (!isFirst) {
            boardCell.setNeighbour(Cell.WEST, boardCells[index-1]);
        }
        if (!isLast) {
            boardCell.setNeighbour(Cell.EAST, boardCells[index+1]);
        }
    }

    public boolean placePiece(Piece piece, int pieceCellIndex, int boardCellIndex) {
        List<BoardCell> occupiedCells = new ArrayList<>(Piece.CELL_COUNT);
        piece.unProcessing();
        match(piece.getCell(pieceCellIndex), boardCells[boardCellIndex], occupiedCells);
        if (occupiedCells.size() == Piece.CELL_COUNT) {
            for(BoardCell boardCell : occupiedCells) {
                boardCell.setPiece(piece);
            }
        }
        return occupiedCells.size() == Piece.CELL_COUNT;
    }

    private void match(Cell pieceCell, BoardCell boardCell, List<BoardCell> occupiedCells) {
        if (pieceCell != null && boardCell != null && !pieceCell.isProcessing() && boardCell.getPiece() == null) {
            pieceCell.setProcessing(true);
            occupiedCells.add(boardCell);
            for (int i = 0; i < Cell.NEIGHBOUR_COUNT; i++) {
                match(pieceCell.getNeighbour(i), (BoardCell) boardCell.getNeighbour(i), occupiedCells);
            }
        }
    }

    public boolean hasIsolatedIsland() {
        return false;
    }

    public BoardCell getCell(int index) {
        return boardCells[index];
    }

    public void removePiece(Piece piece) {
        for (BoardCell boardCell : boardCells) {
            if (piece == boardCell.getPiece()) {
                boardCell.setPiece(null);
            }
        }
    }

    public void unProcessing() {
        for (Cell cell : boardCells) {
            cell.setProcessing(false);
        }
    }

    public int getFirstEmptyCellIndex() {
        for (int i = 0; i < CELL_COUNT; i++) {
            if (boardCells[i].getPiece() == null) return i;
        }
        return -1;
    }
}
