package com.loic.codinGame;

import java.util.Scanner;

import com.loic.codinGame.smash.SmashPlayer;

public class CodinGameMain {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in, "UTF-8");

        CodinGameResolver<?> resolver = new SmashPlayer();

        resolver.before(in);

        while (true) {
            resolver.accept(in, result -> System.out.println(result.toString()));
        }
    }
}
