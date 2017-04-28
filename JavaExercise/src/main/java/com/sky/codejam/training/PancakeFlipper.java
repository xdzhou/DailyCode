package com.sky.codejam.training;

import java.util.Scanner;

import com.sky.codejam.Resolver;

/**
 * https://code.google.com/codejam/contest/3264486/dashboard
 */
public class PancakeFlipper implements Resolver {

    @Override
    public String solve(Scanner in) {
        String cakes = in.next();
        int count = in.nextInt();

        int nb = 0;
        boolean[] flags = new boolean[cakes.length()];
        for (int i = 0; i < flags.length; i++) {
            flags[i] = cakes.charAt(i) == '+';
        }
        for (int i = 0; i < flags.length; i++) {
            if (!flags[i]) {
                if (flags.length - i >= count) {
                    nb++;
                    for (int j = 0; j < count; j++) {
                        flags[i + j] = !flags[i + j];
                    }
                } else {
                    return "IMPOSSIBLE";
                }
            }
        }
        return Integer.toString(nb);
    }
}
