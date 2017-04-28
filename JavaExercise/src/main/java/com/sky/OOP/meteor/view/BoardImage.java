package com.sky.OOP.meteor.view;

import java.awt.*;


/**
 * <p>This class handles the basic imaging functionality.
 * It has an Image, and methods to color cells on the image.
 *
 * @author Kris Cardinaels - krisc@cs.kuleuven.ac.be
 */
public class BoardImage {

    protected Image image;
    protected Graphics theG;

    protected float radius;
    protected int yOffset;
    protected int xOffset;

    protected Polygon[][] polygons = new Polygon[10][10];

    /**
     * <p>Creates a new BoardImage. The Image is initially blank.
     */
    public BoardImage(Image image, int x_size, int y_size) {

        this.image = image;
        theG = image.getGraphics();
        radius = (float) Math.min(((float) x_size) / 13 / Math.cos(Math.PI / 6), ((float) y_size) / (20 * Math.sin(Math.PI / 6) + 11 * (1 - Math.sin(Math.PI / 6)) + 1));
        xOffset = (int) ((x_size - 11 * radius * Math.cos(Math.PI / 6)) / 2 + radius * Math.cos(Math.PI / 6));
        yOffset = (int) ((y_size - radius * (2 * Math.sin(Math.PI / 6) * 10 + 11 * (1 - Math.sin(Math.PI / 6)))) / 2 + radius);
    }


    /**
     * <p>Returns the Image of this BoardImage
     */
    public Image getImage() {
        return image;
    }


    /**
     * <p>Draws the cell for boardposition (x,y) in the given color.
     */
    public void drawCell(int x, int y, Color c) {
        Polygon p = getPolygon(x, y);
        theG.setColor(c);
        theG.fillPolygon(p);
        theG.setColor(Color.black);
        theG.drawPolygon(p);
    }


    /**
     * <p>Draws the entire LightBoardCopy
     */
    public void drawLightBoardCopy(LightBoardCopy board) {
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 5; j++)
                drawCell(i, j, board.getColor(i, j));
    }


    /**
     * <p>Gives the polygon for the boardposition (x,y).
     */
    protected Polygon getPolygon(int x, int y) {
        if (polygons[x][y] == null) {
            polygons[x][y] = createPolygon(x, y);
        }
        return polygons[x][y];
    }


    /**
     * <p>Creates and returns a Polygon for the boardposition (x,y).
     */
    protected Polygon createPolygon(int y, int x) {

        x = x - (y / 2);
        float gapx_right = (float) (radius * 2 * Math.cos(Math.PI / 6));
        float gapx_lowerright = (float) (radius * Math.cos(Math.PI / 6));
        float gapy_lowerright = (float) (radius * (1 + Math.sin(Math.PI / 6)));
        float centerX = xOffset + x * gapx_right + y * gapx_lowerright;
        float centerY = yOffset + y * gapy_lowerright;
        Polygon p = new Polygon();
        for (int i = 0; i <= 5; i++) {
            p.addPoint((int) (centerX + (Math.cos(Math.PI / 6 * (1 + 2 * i)) * radius)), (int) (centerY + (Math.sin(Math.PI / 6 * (1 + 2 * i)) * radius)));
        }
        return p;
    }
}