package com.loic.codinGame.smash;

import java.util.Scanner;


public class SmashPlayer {
    private static final int HEIGHT = 12;
    private static final int WIDTH = 6;
    private static final int NEXT_LEN = 6;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in, "UTF-8");
        Smash.GeneticAlgo algo = new Smash.GeneticAlgo();
        int[] colors = new int[8];

        // game loop
        while (true) {
            for (int i = 0; i < 8; i++) {
                int colorA = in.nextInt(); // color of the first block
                in.nextInt(); // color of the attached block
                colors[i] = colorA;
            }
            for (int i = 0; i < 12; i++) {
                String row = in.next();
                algo.mMapInfo.initLine(i, row);
            }
            for (int i = 0; i < 12; i++) {
                in.next(); // One line of the map ('.' = empty, '0' = skull block, '1' to '5' = colored block)
            }

            System.out.println(algo.start(colors)); // "x": the column in which to drop your blocks
        }
    }
}
