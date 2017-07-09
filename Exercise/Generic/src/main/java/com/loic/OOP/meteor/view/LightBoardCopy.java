package com.loic.OOP.meteor.view;

import java.awt.*;
import java.io.Serializable;

import com.loic.OOP.meteor.Board;


/**
 * <p>A Light copy of the original meteor.*.Board class that contains only
 * a 2 dimentional array of the colors of all the cells of the Board.<br>
 * This class can be serialized to view the solutions of the puzzle.
 *
 * @author Kris Cardinaels - krisc@cs.kuleuven.ac.be
 * @author Maarten De Cock
 */
public class LightBoardCopy implements Serializable {

    /**
     * <p>The colors of the board.
     */
    protected Color[][] colors;


    /**
     * <p>Creates the LightBoardCopy out of the given Board.
     */
    @SuppressWarnings("PMD")
    public LightBoardCopy(Board board) {
        colors = new Color[10][5];

        // for each row
        for (int i = 0; i < 10; i++) {
            // for each column
            for (int j = 0; j < 5; j++) {
                try {
                    int pieceNumber = board.getCell((i * 5) + j).getPiece().getNumber();
                    colors[i][j] = getColor(pieceNumber);
                } catch (NullPointerException npe) {
                    colors[i][j] = Color.white;
                }
            }
        }
    }

    /**
     * <p>Returns the color of the given number.
     */
    public static Color getColor(int number) {

        switch (number) {
            case 0:
                return Color.gray;
            case 1:
                return Color.blue;
            case 2:
                return Color.green;
            case 3:
                return Color.magenta;
            case 4:
                return Color.orange;
            case 5:
                return Color.pink;
            case 6:
                return Color.red;
            case 7:
                return Color.lightGray;
            case 8:
                return Color.darkGray;
            default:
                return Color.yellow;
        }
    }

    /**
     * <p>Returns the color at the given position.
     */
    public Color getColor(int x, int y) {
        return colors[x][y];
    }
}
