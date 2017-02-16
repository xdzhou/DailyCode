package com.sky.OOP.meteor;

public class Cell {
    public static final int NEIGHBOUR_COUNT = 6;

    public static final int EAST = 0;
    public static final int SOUTH_EAST = 1;
    public static final int SOUTH_WEST = 2;
    public static final int WEST = 3;
    public static final int NORTH_WEST = 4;
    public static final int NORTH_EAST = 5;

    private boolean mProcessing = false;

    private Cell[] mNeighbours = new Cell[NEIGHBOUR_COUNT];

    public Cell getNeighbour(int side) {
        if (side >= 0 && side < NEIGHBOUR_COUNT) {
            return mNeighbours[side];
        } else {
            throw new IllegalArgumentException("Unknown Direction : " + side);
        }
    }

    public void setNeighbour(int side, Cell cell) {
        if (side >= 0 && side < NEIGHBOUR_COUNT) {
            mNeighbours[side] = cell;
        } else {
            throw new IllegalArgumentException("Unknown Direction : " + side);
        }
    }

    public void setProcessing(boolean processing) {
        mProcessing = processing;
    }

    public boolean isProcessing() {
        return mProcessing;
    }

    public void flip() {
        Cell c = getNeighbour(EAST);
        setNeighbour(EAST, getNeighbour(WEST));
        setNeighbour(WEST, c);

        c = getNeighbour(SOUTH_EAST);
        setNeighbour(SOUTH_EAST, getNeighbour(SOUTH_WEST));
        setNeighbour(SOUTH_WEST, c);

        c = getNeighbour(NORTH_EAST);
        setNeighbour(NORTH_EAST, getNeighbour(NORTH_WEST));
        setNeighbour(NORTH_WEST, c);
    }

    public void rotate() {
        // Clockwise rotation
        Cell c = getNeighbour(EAST);
        setNeighbour(EAST, getNeighbour(NORTH_EAST));
        setNeighbour(NORTH_EAST, getNeighbour(NORTH_WEST));
        setNeighbour(NORTH_WEST, getNeighbour(WEST));
        setNeighbour(WEST, getNeighbour(SOUTH_WEST));
        setNeighbour(SOUTH_WEST, getNeighbour(SOUTH_EAST));
        setNeighbour(SOUTH_EAST, c);
    }
}
