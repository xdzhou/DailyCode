package com.loic.optimization;

public class MaxIterationException extends RuntimeException {
  public MaxIterationException(int maxIteration) {
    super("Max Iteration got " + maxIteration);
  }
}
