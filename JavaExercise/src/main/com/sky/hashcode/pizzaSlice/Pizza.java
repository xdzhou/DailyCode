package com.sky.hashcode.pizzaSlice;

import java.util.List;

class Pizza {
    private final int rowCount;
    private final int columnCount;

    private boolean[][] board;

    Pizza(int rowCount, int columnCount) {
        this.columnCount = columnCount;
        this.rowCount = rowCount;
        board = new boolean[rowCount][columnCount];
    }

    void setData(int row, int column, boolean isTomato) {
        board[row][column] = isTomato;
    }

    Slice getSlice(int row, int column) {
        return new Slice(row, column, row, column);
    }

    int findFirstAvailableCellIndex(List<Slice> slices, int from) {
        int size = size();
        for (int i = from; i < size; i++) {
            int row = i / columnCount;
            int col = i % columnCount;
            if (isCellAvailable(row, col, slices)) {
                return i;
            }
        }
        return -1;
    }

    int size() {
        return rowCount * columnCount;
    }

    int getColumnCount() {
        return columnCount;
    }

    int getRowCount() {
        return rowCount;
    }

    boolean isTomato(int row, int col) {
        return board[row][col];
    }

    int getTomatoCount(int topLeftRow, int topLeftColumn, int bottomRightRow, int bottomRightColumn) {
        int count = 0;
        for (int row = topLeftRow; row <= bottomRightRow; row ++) {
            for (int column = topLeftColumn; column <= bottomRightColumn; column ++) {
                if (board[row][column]) {
                    count ++;
                }
            }
        }
        return count;
    }

    boolean areCellsAllAvailable(int topLeftRow, int topLeftColumn, int bottomRightRow, int bottomRightColumn, List<Slice> cutSlices) {
        for (int row = topLeftRow; row <= bottomRightRow; row ++) {
            for (int column = topLeftColumn; column <= bottomRightColumn; column ++) {
                if (!isCellAvailable(row, column, cutSlices)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isCellAvailable(int row, int column, List<Slice> cutSlices) {
        for (Slice slice : cutSlices) {
            if (slice.include(row, column)) {
                return false;
            }
        }
        return true;
    }
}
