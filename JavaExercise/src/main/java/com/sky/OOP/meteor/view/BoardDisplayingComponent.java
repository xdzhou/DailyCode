package com.sky.OOP.meteor.view;

import java.awt.*;
import javax.swing.*;

/**
 * <p>The panel that contains the board image.
 *
 * @author Kris Cardinaels - krisc@cs.kuleuven.ac.be
 */
public class BoardDisplayingComponent extends JPanel {
    protected int width = 0, height = 0;
    protected BoardImage boardImage;
    protected LightBoardCopy board;

    /**
     * <p>Creates a BoardDisplayingComponent with the given LightBoardCopy.
     */
    public BoardDisplayingComponent(LightBoardCopy board) {

        super();
        this.board = board;
        recreateAndInitiateBoardImage();

        // Add Listeners when the window is resized.
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent e) {
                recreateAndInitiateBoardImage();
                repaint();
            }

            public void componentShown(java.awt.event.ComponentEvent e) {
                if (boardImage == null) {
                    repaint(); // will also initiate boardImage
                }
            }
        });
    }


    /**
     * <p>Will create and initiate the board image.
     */
    public void recreateAndInitiateBoardImage() {

        width = getWidth();
        height = getHeight();
        Image image = createImage(width, height);
        if (image != null) {
            boardImage = new BoardImage(createImage(width, height), width, height);
            commitBoardToBoardImage();
        }
    }


    /**
     * <p>Sets the board of this component.
     */
    public void setBoard(LightBoardCopy board) {
        this.board = board;
    }


    /**
     * <p>Draws the board on this component.
     */
    protected void commitBoardToBoardImage() {
        boardImage.drawLightBoardCopy(board);
        repaint();
    }


    /**
     * <p>Returns the preferred size of this component.
     */
    public Dimension getPreferredSize() {
        return new Dimension(700, 1000);
    }


    /**
     * <p>Returns the maximum size of this component.
     */
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }


    /**
     * <p>Returns the minimum size of this component.
     */
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }


    /**
     * <p>Pains the component.
     */
    public void paintComponent(Graphics g) {
        if (boardImage == null) {
            recreateAndInitiateBoardImage();
        }
        try {
            g.drawImage(boardImage.getImage(), 0, 0, this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}