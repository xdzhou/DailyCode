package com.loic.codinGame.smash;

class ColorSet {
  private char c1, c2;

  ColorSet(char c1, char c2) {
    this.c1 = c1;
    this.c2 = c2;
  }

  ColorSet(int c1, int c2) {
    this((char) ('0' + c1), (char) ('0' + c2));
  }

  public ColorSet exchange() {
    return new ColorSet(c2, c1);
  }

  public boolean sameColor() {
    return c1 == c2;
  }

  public char color1() {
    return c1;
  }

  public char color2() {
    return c2;
  }

  @Override
  public String toString() {
    return "[" + c1 + ',' + c2 + "]";
  }
}
