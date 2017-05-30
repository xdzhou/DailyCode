package com.sky.hashcode.pizzaSlice;

class Slice {
    int row1;
    int col1;
    int row2;
    int col2;

    int tomatoCount = 0;

    Slice(int row1, int col1, int row2, int col2) {
        this.row1 = row1;
        this.col1 = col1;
        this.row2 = row2;
        this.col2 = col2;
    }

    int size() {
        return (row2 - row1 + 1) * (col2 - col1 + 1);
    }

    boolean include(int row, int column) {
        return row1 <= row &&
            row <= row2 &&
            col1 <= column &&
            column <= col2;
    }

    void extend(Direction dir, Pizza pizza) {
        switch (dir) {
            case DOWN:
                row2++;
                tomatoCount += pizza.getTomatoCount(row2, col1, row2, col2);
                break;
            default:
                col2++;
                tomatoCount += pizza.getTomatoCount(row1, col2, row2, col2);
        }
    }

    void undoExtend(Direction dir, Pizza pizza) {
        switch (dir) {
            case DOWN:
                tomatoCount -= pizza.getTomatoCount(row2, col1, row2, col2);
                row2--;
                break;
            default:
                tomatoCount -= pizza.getTomatoCount(row1, col2, row2, col2);
                col2--;
        }
    }

    boolean isValid(int L, int H) {
        int size = size();
        return size <= H && tomatoCount >= L && size - tomatoCount >= L;
    }

    boolean neverCut(int L, int H) {
        int size = size();
        return H - size < L - tomatoCount || H - size < L - size + tomatoCount;
    }
}