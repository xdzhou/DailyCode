package com.sky.codingame.match;

import java.util.Scanner;

public class MissiontoMarsQ1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in, "UTF-8");
        int N = in.nextInt();
        int sx, sy, indi = 0;
        int px[] = new int[N];
        int py[] = new int[N];
        for (int i = 0; i < N; i++) {
            px[i] = in.nextInt();
            py[i] = in.nextInt();
            if (i > 0 && py[i] == py[i - 1])
                indi = i;
        }
        sx = (px[indi - 1] + px[indi]) / 2;
        sy = py[indi];
        System.out.println("0 4");
        while (true) {
            int X = in.nextInt();
            int Y = in.nextInt();
            int HS = in.nextInt();
            int VS = in.nextInt();
            VS -= 4;
            int F = in.nextInt();
            int R = in.nextInt();
            int P = in.nextInt();

            int dx = sx - X;
            int dy = sy - Y;

            getOutPut(HS, VS, dx, dy);
        }
    }

    private static void getOutPut(int vx, int vy, int dx, int dy) {
        int R = -1, P = 0;
        if (Math.abs(dx) < 500)
            R = 0;
        else {
            int temp = dx / 100 - vx;
            if (temp > 2000) {
                R = -25;
                P = 3;
            } else if (temp > 900) {
                R = -15;
                P = 4;
            } else if (temp > 500) {
                R = -10;
                P = 1;
            } else if (temp < -3000) {
                R = 75;
                P = 4;
            } else if (temp < -2000) {
                R = 25;
                P = 3;
            } else if (temp < -900) {
                R = 15;
                P = 4;
            } else if (temp < -500) {
                R = 10;
                P = 1;
            } else {
                R = (temp > 0) ? -5 : 5;
                P = 1;
            }
            System.out.println(R + " " + P);
            return;
        }

        double rate = 300 / 4d;
        double fdx = dx / rate;
        double fdy = dy / rate;
        fdx += vx;
        fdy += vy;
        rate = Math.sqrt(fdx * fdx + fdy * fdy);
        if (rate > 50)
            P = 4;
        else
            P = 3;
        System.out.println(R + " " + P);

    }

}
