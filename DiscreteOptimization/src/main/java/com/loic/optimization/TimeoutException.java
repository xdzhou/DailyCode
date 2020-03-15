package com.loic.optimization;

public class TimeoutException extends RuntimeException {
  public TimeoutException(long maxMs) {
    super("Timeout reach " + maxMs + " ms");
  }
}
