package com.sky.OOP.meteor.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;


/**
 * <p>Allows to view each solution.
 *
 * @author Maarten De Cock
 */
public class SolutionViewer extends JFrame implements ActionListener {

	private int currentSolution = 1;
	private static final int minSolution = 1;
	private static final int maxSolution = 2098;

	private BoardDisplayingComponent boardDisplay;

	/**
	 * <p>Main method that starts our SolutionViewer.
	 */
	public static void main(String args[]) {
		SolutionViewer gui = new SolutionViewer();
		gui.setSize(500,500);
		gui.setVisible(true);
	}


	/**
	 * <p>Creates the SolutionViewer frame.
	 */
	public SolutionViewer() {

		setTitle("Meteor Solution Viewer: Solution " + currentSolution);
		Container mainFrame = getContentPane();
		mainFrame.setLayout(new BorderLayout());
		boardDisplay = new BoardDisplayingComponent(getLightBoardCopy(getFileName(currentSolution)));
		mainFrame.add(boardDisplay, BorderLayout.CENTER);

		JButton rewindButton = new JButton("<<");
		rewindButton.setActionCommand("rewind");
		rewindButton.addActionListener(this);

		JButton previousButton = new JButton("<");
		previousButton.setActionCommand("previous");
		previousButton.addActionListener(this);

		JButton nextButton = new JButton(">");
		nextButton.setActionCommand("next");
		nextButton.addActionListener(this);

		JButton fastForwardButton = new JButton(">>");
		fastForwardButton.setActionCommand("fastforward");
		fastForwardButton.addActionListener(this);


		JPanel buttonPanel = new JPanel();
		buttonPanel.add(rewindButton);
		buttonPanel.add(previousButton);
		buttonPanel.add(nextButton);
		buttonPanel.add(fastForwardButton);
		mainFrame.add(buttonPanel, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

	}


	/**
	 * <p>Returns the file name of the given solution number.
	 */
	private String getFileName(int solutionNumber) {
		return "solutions/"+solutionNumber+".ser";
	}


	/**
	 * <p>Gets a LightBoardCopy object from the give file name.
	 */
	private LightBoardCopy getLightBoardCopy(String path) {

		// Get the LightBoardCopy from the ser file.
		LightBoardCopy lightBoardCopy = null;
		try  (FileInputStream fis = new FileInputStream(path)) {
			// Issue in jdk 1.4.0
			// http://developer.java.sun.com/developer/bugParade/bugs/4466485.html
			//String path = this.getClass().getClassLoader().getResource(fileName).getPath();
			//path = URLDecoder.decode(path);
			ObjectInputStream bufIn = new ObjectInputStream(fis);
			lightBoardCopy = (LightBoardCopy) bufIn.readObject();
			bufIn.close();
			fis.close();
		}
		catch (IOException | ClassNotFoundException e) {
			System.out.println("Solution file " + path + " not found, make sure you've calculated all solutions.");
			throw new RuntimeException(e);
		}
		return lightBoardCopy;
	}


	/**
	 * <p>Shows the next solution.
	 */
	private void next() {
		if (currentSolution != maxSolution) {
			currentSolution++;
			showCurrentSolution();
		}
	}


	/**
	 * <p>Increments by 100.
	 */
	private void fastForward() {
		currentSolution += 100;
		if (currentSolution > maxSolution) currentSolution = maxSolution;
		showCurrentSolution();
	}


	/**
	 * <p>Shows the previous solution.
	 */
	private void previous() {
		if (currentSolution != minSolution) {
			currentSolution--;
			showCurrentSolution();
		}
	}


	/**
	 * <p>Substracts by 100.
	 */
	private void rewind() {
		currentSolution -= 100;
		if (currentSolution < minSolution) currentSolution = minSolution;
		showCurrentSolution();
	}


	/**
	 * <p>Displays the current solution.
	 */
	private void showCurrentSolution() {
		boardDisplay.setBoard(getLightBoardCopy(getFileName(currentSolution)));
		boardDisplay.recreateAndInitiateBoardImage();
		setTitle("Meteor Solution Viewer: Solution " + currentSolution);
	}


    /**
     * <p>Calls the correct actions when the buttons are pressed.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("next")) {
            next();
        }
        else if (e.getActionCommand().equals("previous")) {
			previous();
        }
        else if (e.getActionCommand().equals("rewind")) {
			rewind();
        }
        else if (e.getActionCommand().equals("fastforward")) {
			fastForward();
        }
    }
}