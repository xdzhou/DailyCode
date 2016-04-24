package com.sky.codingame.training;

import java.util.Scanner;

public class Temperatures {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int small = 10001;
        int n = in.nextInt();
        if (n == 0) {
            System.out.println(0);
            in.close();
            return;
        }
        for (int i = 0; i < n; i++) {
            if (small == 10001) {
                small = in.nextInt();
            } else {
                int nextValut = in.nextInt();
                if (Math.abs(nextValut) < Math.abs(small)) {
                    small = nextValut;
                }
                if (Math.abs(nextValut) == Math.abs(small)) {
                    small = (nextValut > small) ? nextValut : small;
                }
            }
        }

        System.out.println(small);
        in.close();
    }

}
