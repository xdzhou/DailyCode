package com.loic.codinGame;

import com.loic.codinGame.smash.SmashPlayer;

import java.util.Scanner;

public class CodinGameMain {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in, "UTF-8");

    CodinGameResolver<?> resolver = new SmashPlayer();

    resolver.before(in);

    while (true) {
      System.out.println(resolver.accept(in));
    }
  }
}
