package com.loic.optimization.knapsack;

public class Treasure {

  private final int id;
  private final int weight, value;
  private final double density;

  public Treasure(int id, int weight, int value) {
    this.id = id;
    this.weight = weight;
    this.value = value;
    density = value / (double) weight;
  }

  public int id() {
    return id;
  }

  public int weight() {
    return weight;
  }

  public int value() {
    return value;
  }

  public double density() {
    return density;
  }
}