package com.loic.codinGame.smash;

public class Drop {
    private final int column;
    private final int rotation;


    public Drop(int column, int rotation) {
        this.column = column;
        this.rotation = rotation;
    }

    public int column() {
        return column;
    }

    public int rotation() {
        return rotation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Drop drop = (Drop) o;

        if (column != drop.column) {
            return false;
        }
        return rotation == drop.rotation;
    }

    @Override
    public int hashCode() {
        int result = column;
        result = 31 * result + rotation;
        return result;
    }

    @Override
    public String toString() {
        return "Drop{" +
            "column=" + column +
            ", rotation=" + rotation +
            '}';
    }
}
